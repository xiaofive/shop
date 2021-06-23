package com.shop.cart.cart.dao.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import com.shop.cart.cart.bean.CartConstant;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import com.shop.cart.cart.bean.dto.SpProductCacheDTO;
import com.shop.cart.cart.bean.vo.SpCartInfo;
import com.shop.cart.cart.dao.SpCartCacheDAO;
import com.shop.cart.cart.dao.SpSelectStatusDAO;
import com.shop.cart.feign.ResdisClusterFeignClient;
import com.shop.cart.feign.ShopProductFeignClient;
import com.shop.common.dto.redis.RedisDTO;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    private ShopProductFeignClient sfc;

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
        splitKey.stream().forEach(field -> rfc.hdel(CartConstant.CART_CACHE + userKey, field));
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
            redisDTO.setKey(CartConstant.CART_CACHE + userKey)
                    .setField(splitKey)
                    .setValue(JSON.toJSONString(cartCacheDTO));
            rfc.hset(redisDTO);
        }
        if (selected != null) {
            RedisDTO redisDTO = new RedisDTO();
            redisDTO.setKey(CartConstant.CART_SELECT_CACHE + userKey)
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
            redisDTO.setKey(CartConstant.CART_SELECT_CACHE + userKey)
                    .setField(field)
                    .setValue(seleted.toString());
            rfc.hset(redisDTO);
        });
    }

    @Override
    public SpCartInfo getCart(String userKey) {
        Map<String, String> cartMap = rfc.hgetAll(CartConstant.CART_CACHE + userKey);
        log.warn("cache cart res:{}", cartMap);
        if (MapUtil.isEmpty(cartMap)) {
            return null;
        }
        //获取购物车
        Collection<String> cartJsonStrList = cartMap.values();
        List<SpCartCacheDTO> spCartCacheDTOS = JSONObject.parseArray(cartJsonStrList.toString(), SpCartCacheDTO.class);

        //获取商品数据
        List<Long> productIds = spCartCacheDTOS.stream().map(SpCartCacheDTO::getProductId).collect(Collectors.toList());
        List<SpProductCacheDTO> products = sfc.getProductListByIds(productIds);

        //获取选中状态
        Map<String, String> selectMap = rfc.hgetAll(CartConstant.CART_SELECT_CACHE + userKey);

        SpCartInfo cart = new SpCartInfo();
        //购物车总品项数
        cart.setTotalQty(spCartCacheDTOS.size());

        Pair<BigDecimal, Pair<BigDecimal, BigDecimal>> totalPricePair = calculationSelectProTotalPrice(selectMap, products);
        //购物车总价
        cart.setCartTotalPrice(totalPricePair.getKey());
        //购物车选中商品总价
        cart.setSelectProTotalPrice(totalPricePair.getValue().getKey());
        //行销活动优惠金额
        cart.setCampaignDiscount(totalPricePair.getValue().getValue());

        BigDecimal freightPrice = calculationFreightPrice(selectMap, products);
        //购物车运费
        cart.setFreightPrice(freightPrice);
        //购物车是否全选
        boolean isSelectAll = !MapUtil.isNotEmpty(selectMap) || !selectMap.containsValue("0");
        cart.setIsSelectAll(isSelectAll);

        //购物车品相列表
        cart.setProductVOList(products);

        return cart;
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
                cartDTO.setKey(CartConstant.CART_CACHE + loginKey)
                        .setField(unLoginCartVO.getKey())
                        .setValue(unLoginCartVO.getValue());
                rfc.hset(cartDTO);
                //del unLogin cart
                rfc.hdel(CartConstant.CART_CACHE + unLoginKey, unLoginCartVO.getKey());
            });
            return;
        }

        Set<String> _loginKeys = _loginCart.keySet();
        Set<String> _unloginKeys = _loginCart.keySet();

        Set<String> result = Sets.newHashSet();
        result.addAll(_loginKeys);
        result.retainAll(_unloginKeys);

        result.stream().forEach(field -> {
            String loginCartStr = _loginCart.get(field);
            SpCartCacheDTO loginCartDTO = JSONObject.parseObject(loginCartStr, SpCartCacheDTO.class);

            String unLoginCartStr = _loginCart.get(field);
            SpCartCacheDTO unLoginCartDTO = JSONObject.parseObject(unLoginCartStr, SpCartCacheDTO.class);
            //merge ---> unLogin ---> new
            loginCartDTO.setQty(loginCartDTO.getQty() + unLoginCartDTO.getQty());
            loginCartDTO.setAddPrice(unLoginCartDTO.getAddPrice());
            RedisDTO mergeCartDTO = new RedisDTO();
            mergeCartDTO.setKey(CartConstant.CART_CACHE + loginKey)
                    .setField(loginCartDTO.getSplitKey())
                    .setValue(JSON.toJSONString(loginCartDTO));
            rfc.hset(mergeCartDTO);
            //del unLogin cart
            rfc.hdel(CartConstant.CART_CACHE + unLoginKey, unLoginCartDTO.getSplitKey());
        });

        result.clear();
        //del all right merge cart
        result.addAll(_unloginKeys);
        result.removeAll(_loginKeys);

        result.stream().forEach(unLoginCartKey -> {
            String unLoginNotMergeStr = _loginCart.get(unLoginCartKey);
            SpCartCacheDTO unLoginNotMergeCart = JSONObject.parseObject(unLoginNotMergeStr, SpCartCacheDTO.class);
            RedisDTO unLoginNotMergeCartDTO = new RedisDTO();
            unLoginNotMergeCartDTO.setKey(CartConstant.CART_CACHE + loginKey)
                    .setField(unLoginNotMergeCart.getSplitKey())
                    .setValue(JSON.toJSONString(unLoginNotMergeCartDTO));
            rfc.hset(unLoginNotMergeCartDTO);
            //del unLogin cart
            rfc.hdel(CartConstant.CART_CACHE + unLoginCartKey, unLoginNotMergeCart.getSplitKey());
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

    /**
     * 计算选购物车中选中：
     * 购物车总价 left
     * 选中商品总价 r-left
     * 行销优惠 r-right
     *
     * @param selectMap
     * @param productCacheDTOS
     * @return: java.math.BigDecimal
     * @Date: 2021-06-23
     */
    private Pair<BigDecimal, Pair<BigDecimal, BigDecimal>> calculationSelectProTotalPrice(Map<String, String> selectMap, List<SpProductCacheDTO> productCacheDTOS) {
        //TODO
        return new Pair<>(BigDecimal.TEN, new Pair<>(BigDecimal.TEN, BigDecimal.ZERO));
    }

    /**
     * 计算运费
     *
     * @param selectMap
     * @param productCacheDTOS
     * @return: java.math.BigDecimal
     * @Date: 2021-06-23
     */
    private BigDecimal calculationFreightPrice(Map<String, String> selectMap, List<SpProductCacheDTO> productCacheDTOS) {
        //TODO
        return BigDecimal.ONE;
    }

}
