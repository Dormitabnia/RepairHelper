package com.ss.rh.service.impl;

import com.ss.rh.dao.UserMapper;
import com.ss.rh.entity.User;
import com.ss.rh.entity.UserExample;
import com.ss.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKey(user) > 0;
    }

    @Override
    public boolean deleteUserById(int id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<User> getUserList() {
        UserExample userExample = new UserExample();

        return userMapper.selectByExample(userExample);
    }
}
