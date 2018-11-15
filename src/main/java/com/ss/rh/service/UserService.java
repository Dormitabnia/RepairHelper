package com.ss.rh.service;

import com.ss.rh.entity.User;

public interface UserService {

    public User getUserById(int id);

    public boolean insertUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUserById(int id);

}
