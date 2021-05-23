package com.shop.product.rest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.product.bean.entity.User;
import com.shop.product.bean.req.UserReq;
import com.shop.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 测试 done
     * validations
     * Mybatis-Plus
     * 分页 //TODO
     * Swagger-ui
     * 数据源配置
     * lombok
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @PostMapping("page")
    public Page<User> testPage(@Valid @RequestBody UserReq userReq) {
        Page page = new Page(1, 2);
        return userService.testSelect(page, 1L);
    }

    /**
     * 测试
     * MyBatis-Plus 自定义SQL注入器
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @GetMapping
    public User testSelectByRefId(@RequestParam String refId) {
        return userService.testSelectByRefId(refId);
    }

    /**
     * 测试Mybatis-Plus done
     * 乐观锁
     * updated字段时间戳
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @PutMapping
    public void testUpdate(@Valid @RequestBody UserReq userReq) {
        userService.testUpdate(userReq);
    }

    /**
     * 测试 done
     * MyBatis-Plus
     * 软删除
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @DeleteMapping
    public void testDelete(@RequestParam Long id) {
        userService.testDelete(id);
    }

    /**
     * 测试
     * Mybatis-Plus
     * 自动填充字段值
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @PostMapping
    public void testInsert(@Valid @RequestBody UserReq userReq) {
        userService.testInsert(userReq);
    }

}
