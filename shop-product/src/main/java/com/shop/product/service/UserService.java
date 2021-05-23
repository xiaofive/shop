package com.shop.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.product.bean.entity.User;
import com.shop.product.bean.req.UserReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface UserService {

    Page<User> testSelect(Page page, Long id);

    User testSelectByRefId(String refId);

    void testUpdate(@Valid @RequestBody UserReq userReq);

    void testDelete(@RequestParam Long id);

    void testInsert(@Valid @RequestBody UserReq userReq);
}
