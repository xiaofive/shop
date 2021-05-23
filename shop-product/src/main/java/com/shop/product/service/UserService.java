package com.shop.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.product.bean.entity.User;
import com.shop.product.bean.req.UserReq;

public interface UserService {

    IPage<User> testPage(Page<User> page, String name);

    User testSelectByRefId(String refId);

    void testUpdate(UserReq userReq);

    void testDelete(Long id);

    void testInsert(UserReq userReq);

    void deleteAllTable();

    void updateAllTable();

}
