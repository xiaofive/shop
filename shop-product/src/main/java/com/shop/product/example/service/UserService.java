package com.shop.product.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.product.example.bean.entity.User;
import com.shop.product.example.bean.req.UserReq;

public interface UserService extends IService<User> {

    IPage<User> testPage(Page<User> page, String name);

    User testSelectByRefId(String refId);

    void testUpdate(UserReq userReq);

    void testDelete(Long id);

    void testInsert(UserReq userReq);

    void deleteAllTable();

    void updateAllTable();

}
