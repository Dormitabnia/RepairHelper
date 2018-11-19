package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.entity.User;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    获取用户信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/userInfo")
    public User getUserInfo(@RequestParam("id") int id) { return userService.getUserById(id); }

    /*
    新增用户（注册）
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public String addUser(@RequestBody User user) {
        boolean flag = false;

        // TODO:获取微信认证信息 直接从前端获取，创建微信认证实体？

        return JsonUtil.success("用户创建成功");
    }

    /*
    修改用户信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/userInfo")
    public boolean modifyUserInfo(@RequestBody User user, HttpSession session) {
        if (session.getAttribute("user") == null)
            return false;

        return userService.updateUser(user);
    }


    /*
    删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE, value = "/userInfo")
//    public boolean deleteUserInfo(@RequestParam int id) {
//        return userService.deleteUserById(id);
//    }

    /*
    登录
     */
//    @ResponseBody()
//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public String
}
