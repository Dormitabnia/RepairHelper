package com.ss.rh.service.impl;

import com.ss.rh.dao.UserMapper;
import com.ss.rh.entity.User;
import com.ss.rh.entity.UserExample;
import com.ss.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
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

    @Override
    public List<User> getUsersLike(String qt, String q, boolean isString) throws Exception {
        String qtname;
        String qstr = "%" + q + "%";

        if(!Character.isUpperCase(qt.charAt(0)))
            qtname = new StringBuilder().append(Character.toUpperCase(qt.charAt(0))).append(qt.substring(1)).toString();
        else
            qtname = qt;

        UserExample ue = new UserExample();

        Class cl = UserExample.Criteria.class;

        Method method;

        if (isString) {
            method = cl.getMethod("and" + qtname + "Like", String.class);
            method.invoke(ue.createCriteria(), qstr);
        }
        else {
            method = cl.getMethod("and" + qtname + "EqualTo", Integer.class);
            method.invoke(ue.createCriteria(), Integer.parseInt(q));
        }

        return userMapper.selectByExample(ue);
    }
}
