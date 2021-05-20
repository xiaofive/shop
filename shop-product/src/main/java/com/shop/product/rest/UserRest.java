package com.shop.product.rest;

import com.shop.product.bean.entity.User;
import com.shop.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MySQL
 * Druid
 * MyBatis-Plus
 * Lombok
 * 集成测试
 *
 * Author: wang Y
 * Date: 2021-05-20
 */
@RequestMapping("/rest/user")
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> testSelect() {
        return userService.testSelect();
    }

}
