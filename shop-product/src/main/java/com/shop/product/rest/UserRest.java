package com.shop.product.rest;

import com.shop.product.bean.entity.User;
import com.shop.product.bean.req.UserReq;
import com.shop.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * MySQL
 * Druid
 * MyBatis-Plus
 * Lombok
 * 集成测试
 * <p>
 * Author: wang Y
 * Date: 2021-05-20
 */
@RequestMapping("/rest/user")
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @PostMapping
    public List<User> testSelect(@Valid @RequestBody UserReq userReq) {
        return userService.testSelect();
    }

}
