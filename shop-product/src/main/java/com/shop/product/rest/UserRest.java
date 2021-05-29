package com.shop.product.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
     * 分页
     * Swagger-ui
     * 数据源配置
     * lombok
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @GetMapping("page")
    public IPage<User> testPage(@RequestParam(value = "current", defaultValue = "1") Long current,
                                @RequestParam(value = "size", defaultValue = "15") Long size,
                                @RequestParam(required = false) String name) {
        Page page = new Page(current, size);
        return userService.testPage(page, name);
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
     * 测试 done
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

    /**
     * 测试全表删除 done
     * Mybatis-Plus3.4的漏洞：与逻辑删除字段共用的时候拦截失效的问题
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @GetMapping("allTableDelete")
    public void allTableDelete() {
        userService.deleteAllTable();
    }

    /**
     * 测试全表更新 done
     * Mybatis-Plus3.4的漏洞：与逻辑删除字段共用的时候拦截失效的问题
     *
     * @Param:
     * @return:
     * @Date: 2021-05-23
     */
    @GetMapping("allTableUpdate")
    public void allTableUpdate() {
        userService.updateAllTable();
    }

}
