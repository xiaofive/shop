package com.shop.cart.cart.dao.impl;

import com.alibaba.fastjson.JSON;
import com.shop.cart.cart.bean.dto.SpCartCacheDTO;
import com.shop.cart.cart.dao.SpCartCacheDAO;
import com.shop.cart.feign.ResdisClusterFeignClient;
import com.shop.common.dto.redis.RedisDTO;
import com.shop.common.util.ShiroUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 购物车缓存操作
 * TODO 持久化时机 淘汰策略
 * <p>
 * Author: wang Y
 * Date: 2021-06-14
 */
@Service
public class SpCartCacheDAOImpl implements SpCartCacheDAO {


    @Resource
    private ResdisClusterFeignClient rfc;

    /**
     * 用户已登录
     * TODO 事务_监听watch 重复点击 原子性
     * <p>
     * * 1）查询redis中的数据
     * * 2）如果redis中已经有了，则追加数量，重新计算金额
     * * 3）如果没有，将商品添加到缓存
     * * 4）完全依赖后端返回的数据，每一次 +  和  -  都是后端计算，并且将整个购物车计算结果返回。
     *
     * @param spCartCacheDTO
     * @return: void
     * @Date: 2021-06-17
     */
    @Override
    public void addCart(SpCartCacheDTO spCartCacheDTO) {
        String userId = ShiroUtils.getCurrentUserId();

        SpCartCacheDTO _cartCacheDTO = (SpCartCacheDTO) rfc.hget(userId, spCartCacheDTO.getSplitKey());
        if (null != _cartCacheDTO) {
            _cartCacheDTO.setAddPrice(spCartCacheDTO.getAddPrice());
            _cartCacheDTO.setQty(spCartCacheDTO.getQty().add(_cartCacheDTO.getQty()));
        } else {
            _cartCacheDTO = spCartCacheDTO;
        }
        RedisDTO redisDTO = new RedisDTO();
        redisDTO.setKey(userId)
                .setField(_cartCacheDTO.getSplitKey())
                .setValue(JSON.toJSONString(_cartCacheDTO));

        rfc.hset(redisDTO);

    }

}
