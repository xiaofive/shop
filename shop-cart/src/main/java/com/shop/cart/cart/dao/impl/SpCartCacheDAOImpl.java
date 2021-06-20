package com.shop.cart.cart.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.cart.cart.bean.CartConstant;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import com.shop.cart.cart.dao.SpCartCacheDAO;
import com.shop.cart.cart.dao.SpSelectStatusDAO;
import com.shop.cart.feign.ResdisClusterFeignClient;
import com.shop.common.dto.redis.RedisDTO;
import com.shop.common.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

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
//
//    @Override
//    public void mergeToCart(List<SpCartCacheDTO> unLoginCarts) {
//
//        if (CollectionUtils.isEmpty(unLoginCarts)) {
//            return;
//        }
//
//        Long userId = ShiroUtils.getCurrentUserId();
//        Map<String, String> _cartDTOs = rfc.hgetAll(CartConstant.CART_CACHE + userId);
//        log.warn("cache cart res:{}", JSON.toJSONString(_cartDTOs));
//
//        if (MapUtil.isEmpty(_cartDTOs)) {
//            unLoginCarts.stream().forEach(this::addCart());
//            return;
//        }
//
//        for (SpCartCacheDTO unLoginCart : unLoginCarts) {
//            if (_cartDTOs.containsKey(unLoginCart.getSplitKey())) {
//                String _cartDTOStr = _cartDTOs.get(unLoginCart.getSplitKey());
//                SpCartCacheDTO _cartCacheDTO = JSONObject.parseObject(_cartDTOStr, SpCartCacheDTO.class);
//                _cartCacheDTO.setAddPrice(unLoginCart.getAddPrice())
//                        .setQty(_cartCacheDTO.getQty() + unLoginCart.getQty());
//                addCart(_cartCacheDTO);
//                continue;
//            }
//            addCart(unLoginCart);
//        }
//
//
//    }

    @Override
    public Integer countCartItemQty() {
        Long userId = ShiroUtils.getCurrentUserId();
        Map<String, String> _cartDTOs = rfc.hgetAll(CartConstant.CART_CACHE + userId);
        Integer total = 0;
        for (String item : _cartDTOs.keySet()) {
            String ItemStr = _cartDTOs.get(item);
            SpCartCacheDTO cartCacheDTO = JSONObject.parseObject(ItemStr, SpCartCacheDTO.class);
            total = total + Integer.valueOf(cartCacheDTO.getQty());
        }
        return total;
    }
}
