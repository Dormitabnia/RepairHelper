package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.constants.Constants;
import com.ss.rh.entity.User;
import com.ss.rh.service.UserService;
import com.ss.rh.util.HttpUtil;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
    获取用户信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/userInfo")
    public String getUserInfo(@RequestParam("id") int id) {
        User user = userService.getUserById(id);

        if (user == null)
            return JsonUtil.failure("无法找到该用户", 404);

        return JsonUtil.success("查询成功", user);
    }

    /*
    新增用户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/userInfo")
    public String addUser(@RequestParam("code") String code) {
        if (code.isEmpty())
            return JsonUtil.failure("code为空，用户未授权");

        //通过code获取access_token
        Map<String,Object> params = new HashMap<>();
        params.put("appid", Constants.mpAppId);
        params.put("secret", Constants.mpSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String accessTokenResponse = HttpUtil.get(Constants.accessTokenUrl, params);

        System.out.println("accessTokenResponse : " + accessTokenResponse);
        Map<String, Object> map = JsonUtil.json2Map(accessTokenResponse);

        // TODO:将用户信息存入redis以及mysql

        return JsonUtil.success("用户创建成功");
    }

    /*
    修改用户信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/userInfo")
    public String modifyUserInfo(@RequestBody User user) {
        boolean flag = userService.updateUser(user);

        if (!flag)
            return JsonUtil.failure("修改失败", 500);

        return JsonUtil.success("修改成功");
    }

    /*
    删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE, value = "/userInfo")
//    public boolean deleteUserInfo(@RequestParam int id) {
//        return userService.deleteUserById(id);
//    }

}
