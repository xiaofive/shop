package com.shop.cart.cart.dao.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.shop.cart.cart.bean.CartConstant;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import com.shop.cart.cart.dao.SpCartCacheDAO;
import com.shop.cart.cart.dao.SpSelectStatusDAO;
import com.shop.cart.feign.ResdisClusterFeignClient;
import com.shop.common.dto.redis.RedisDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 购物车缓存操作
 * TODO 持久化时机 淘汰策略
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@Slf4j
@Service
public class SpCartCacheDAOImpl implements SpCartCacheDAO {


    @Resource
    private ResdisClusterFeignClient rfc;

    @Resource
    private SpSelectStatusDAO statusDAO;

    /**
     * 用户已登录
     * TODO Redis事务_监听watch(保存旧值) -> multi(CAS) -> exec（取消watch） ->  重复点击 原子性 ---> redis集群情况下呢 --->测试代码
     * TODO 商品数量上限
     * TODO 各种返回状态码
     * <p>
     * * 1）查询redis中的数据
     * * 2）如果redis中已经有了，则追加数量，重新计算金额
     * * 3）如果没有，将商品添加到缓存
     * * 4）完全依赖后端返回的数据，每一次 +  和  -  都是后端计算，并且将整个购物车计算结果返回。
     * <p>
     * 全部不执行的情况有 1.没有执行EXEC命令 2.WATCH的key发生改变 3.DISCARD命令放弃事务。
     *
     * @param spCartCacheDTO
     * @return: void
     * @Date: 2021-06-17
     */
    @Override
    public void addCart(String userKey, SpCartCacheDTO spCartCacheDTO) {

        //校验商品是否存在
        //skuId/规格 是否存在
        //校验商品信息是否合法 是否上架 库存 最大购买数量....
        if (spCartCacheDTO == null) {
            return;
        }

        SpCartCacheDTO _cartCacheDTO = null;
        String cartJsonStr = rfc.hget(CartConstant.CART_CACHE + userKey, spCartCacheDTO.getSplitKey());
        log.warn("cache cart res:{}", cartJsonStr);
        if (StringUtils.isNotBlank(cartJsonStr)) {
            _cartCacheDTO = JSONObject.parseObject(cartJsonStr, SpCartCacheDTO.class);
        }
        if (null != _cartCacheDTO) {
            _cartCacheDTO.setAddPrice(spCartCacheDTO.getAddPrice());
            _cartCacheDTO.setQty(spCartCacheDTO.getQty() + _cartCacheDTO.getQty());
        } else {
            _cartCacheDTO = spCartCacheDTO;
        }
        RedisDTO redisDTO = new RedisDTO();
        redisDTO.setKey(CartConstant.CART_CACHE + userKey)
                .setField(_cartCacheDTO.getSplitKey())
                .setValue(JSON.toJSONString(_cartCacheDTO));

        rfc.hset(redisDTO);

        RedisDTO redisSelectDTO = new RedisDTO();
        redisSelectDTO.setKey(CartConstant.CART_SELECT_CACHE + userKey)
                .setField(_cartCacheDTO.getSplitKey())
                .setValue(CartConstant.CART_ITEM_SELECT);
        rfc.hset(redisSelectDTO);

    }

    @Override
    public void delFromCart(String userKey, List<String> splitKey) {
        splitKey.stream().forEach(field -> rfc.hdel(userKey, field));
    }

    @Override
    public void updateCart(String userKey, String splitKey, Integer qty, Integer selected) {
        String cartJsonStr = rfc.hget(CartConstant.CART_CACHE + userKey, splitKey);
        log.warn("cache cart res:{}", cartJsonStr);
        if (StringUtils.isBlank(cartJsonStr)) {
            return;
        }
        SpCartCacheDTO cartCacheDTO = JSONObject.parseObject(cartJsonStr, SpCartCacheDTO.class);
        if (qty != null) {
            cartCacheDTO.setQty(qty);
            RedisDTO redisDTO = new RedisDTO();
            redisDTO.setKey(userKey)
                    .setField(splitKey)
                    .setValue(JSON.toJSONString(cartCacheDTO));
            rfc.hset(redisDTO);
        }
        if (selected != null) {
            RedisDTO redisDTO = new RedisDTO();
            redisDTO.setKey(userKey)
                    .setField(splitKey)
                    .setValue(selected.toString());
            rfc.hset(redisDTO);
        }
    }

    @Override
    public void updateSeleted(String userKey, Integer seleted) {
        Map<String, String> selectMap = rfc.hgetAll(CartConstant.CART_SELECT_CACHE + userKey);
        log.warn("cache cart res:{}", selectMap);
        if (MapUtil.isEmpty(selectMap)) {
            return;
        }
        selectMap.keySet().stream().forEach(field -> {
            RedisDTO redisDTO = new RedisDTO();
            redisDTO.setKey(userKey)
                    .setField(field)
                    .setValue(seleted.toString());
            rfc.hset(redisDTO);
        });
    }

    @Override
    public void mergeToCart(String loginKey, String unLoginKey) {
        if (StringUtils.isBlank(loginKey) || StringUtils.isBlank(unLoginKey)) {
            return;
        }
        Map<String, String> _unloginCart = rfc.hgetAll(CartConstant.CART_CACHE + unLoginKey);
        log.warn("cache cart _unloginCart:{}", JSON.toJSONString(_unloginCart));
        if (MapUtil.isEmpty(_unloginCart)) {
            return;
        }
        Map<String, String> _loginCart = rfc.hgetAll(CartConstant.CART_CACHE + loginKey);
        log.warn("cache cart _loginCart:{}", JSON.toJSONString(_loginCart));

        if (MapUtil.isEmpty(_loginCart)) {
            _unloginCart.entrySet().stream().forEach(unLoginCartVO -> {
                RedisDTO cartDTO = new RedisDTO();
                cartDTO.setKey(loginKey)
                        .setField(unLoginCartVO.getKey())
                        .setValue(unLoginCartVO.getValue());
                rfc.hset(cartDTO);
                //del unLogin cart
                rfc.hdel(unLoginKey, unLoginCartVO.getKey());
            });
            return;
        }

        Set<String> _loginKeys = _loginCart.keySet();
        Set<String> _unloginKeys = _loginCart.keySet();

        Set<String> result = Sets.newHashSet();
        result.addAll(_loginKeys);
        result.retainAll(_unloginKeys);

        result.stream().forEach(occurKey -> {
            String loginCartStr = _loginCart.get(occurKey);
            SpCartCacheDTO loginCartDTO = JSONObject.parseObject(loginCartStr, SpCartCacheDTO.class);

            String unLoginCartStr = _loginCart.get(occurKey);
            SpCartCacheDTO unLoginCartDTO = JSONObject.parseObject(unLoginCartStr, SpCartCacheDTO.class);
            //merge ---> unLogin ---> new
            loginCartDTO.setQty(loginCartDTO.getQty() + unLoginCartDTO.getQty());
            loginCartDTO.setAddPrice(unLoginCartDTO.getAddPrice());
            RedisDTO mergeCartDTO = new RedisDTO();
            mergeCartDTO.setKey(loginKey)
                    .setField(loginCartDTO.getSplitKey())
                    .setValue(JSON.toJSONString(loginCartDTO));
            rfc.hset(mergeCartDTO);
            //del unLogin cart
            rfc.hdel(unLoginKey, unLoginCartDTO.getSplitKey());
        });

        result.clear();
        //del all right merge cart
        result.addAll(_unloginKeys);
        result.removeAll(_loginKeys);

        result.stream().forEach(unLoginCartKey -> {
            String unLoginNotMergeStr = _loginCart.get(unLoginCartKey);
            SpCartCacheDTO unLoginNotMergeCart = JSONObject.parseObject(unLoginNotMergeStr, SpCartCacheDTO.class);
            RedisDTO unLoginNotMergeCartDTO = new RedisDTO();
            unLoginNotMergeCartDTO.setKey(loginKey)
                    .setField(unLoginNotMergeCart.getSplitKey())
                    .setValue(JSON.toJSONString(unLoginNotMergeCartDTO));
            rfc.hset(unLoginNotMergeCartDTO);
            //del unLogin cart
            rfc.hdel(unLoginCartKey, unLoginNotMergeCart.getSplitKey());
        });

        log.info("merge loginCart and unLoginCart Cache done!");

    }

    @Override
    public Integer countCartItemQty(String userKey) {
        Map<String, String> cartDTOs = rfc.hgetAll(CartConstant.CART_CACHE + userKey);
        Integer total = 0;
        for (String item : cartDTOs.keySet()) {
            String ItemStr = cartDTOs.get(item);
            SpCartCacheDTO cartCacheDTO = JSONObject.parseObject(ItemStr, SpCartCacheDTO.class);
            total = total + Integer.valueOf(cartCacheDTO.getQty());
        }
        return total;
    }
}
