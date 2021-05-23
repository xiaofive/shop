package com.shop.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.common.util.BeanConvertUtils;
import com.shop.product.bean.entity.User;
import com.shop.product.bean.req.UserReq;
import com.shop.product.mapper.UserMapper;
import com.shop.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> testSelect(Page page, Long id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Page<User> userList = userMapper.selectPage(page, queryWrapper);
        return userList;
    }

    @Override
    public User testSelectByRefId(String refId) {
        User user = userMapper.selectByRefId(refId);
        return user;
    }

    @Override
    public void testUpdate(UserReq userReq) {
        User old = userMapper.selectById(userReq.getId());
        old.setRefId(UUID.randomUUID().toString());
        old.setName(userReq.getName());
        old.setAge(userReq.getAge());
        old.setEmail(userReq.getEmail());
        userMapper.updateById(old);
    }

    @Override
    public void testDelete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void testInsert(UserReq userReq) {
        User user = BeanConvertUtils.map(userReq, User.class);
        user.setRefId(UUID.randomUUID().toString());
        userMapper.insert(user);
    }


}
