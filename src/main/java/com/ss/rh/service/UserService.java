package com.ss.rh.service;

import com.ss.rh.entity.User;

public interface UserService {

    User getUserById(int id);

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUserById(int id);

}
