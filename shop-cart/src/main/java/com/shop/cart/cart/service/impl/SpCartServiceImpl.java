package com.shop.cart.cart.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.cart.cart.bean.entity.SpCart;
import com.shop.cart.cart.bean.req.SpCartReq;
import com.shop.cart.cart.bean.vo.SpCartVO;
import com.shop.cart.cart.mapper.SpCartMapper;
import com.shop.cart.cart.service.SpCartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpCartServiceImpl extends ServiceImpl<SpCartMapper, SpCart> implements SpCartService {

    @Resource
    private SpCartMapper spCartMapper;

    @Override
    public void addUserCart(SpCartReq spCartReq) {

    }

    @Override
    public void deleteCart(Long id) {

    }

    @Override
    public void editUserCart(SpCartReq spCartReq) {

    }

    @Override
    public List<SpCartVO> getUserCartList() {
        return null;
    }

}
