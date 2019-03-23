package com.ss.rh.service;

import com.ss.rh.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(int id);

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(int id);

    List<User> getUserList();

    List<User> getUsersLike(String qt, String q, boolean isString) throws Exception;

}
