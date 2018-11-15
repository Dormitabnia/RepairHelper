package com.ss.rh.controller.wx;

import com.ss.rh.entity.User;
import com.ss.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    获取用户信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/userInfo")
    public User getUserInfo(@RequestParam int id) { return userService.getUserById(id); }

    /*
    新增用户（注册）
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public boolean addUser(User user) {
        boolean flag = false;

        // TODO:获取微信认证信息 直接从前端获取，创建微信认证实体？

        return flag;
    }

    /*
    修改用户信息
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/userInfo")
    public boolean modifyUserInfo(User user) {
        return userService.updateUser(user);
    }

    /*
    删除用户信息
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/userInfo")
    public boolean deleteUserInfo(@RequestParam int id) {
        return userService.deleteUserById(id);
    }

    /*
    登录
     */
//    @ResponseBody()
//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public String

}