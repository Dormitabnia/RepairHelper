package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.User;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    用户登录
     */
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(@RequestParam("code") String code) {
        if (code.isEmpty())
            return JsonUtil.failure("code为空，用户未授权");

        //通过code获取用户session
        Map map = TokenUtil.getSessionByJsCode(code);

        return null;
    }

    /*
    新增用户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/auth")
    public String addUser(@RequestParam("code") String code) {
        if (code.isEmpty())
            return JsonUtil.failure("code为空，用户未授权");

        //通过code获取用户session
        Map map = TokenUtil.getSessionByJsCode(code);

        // TODO:将用户session存入redis以及mysql
        if (map != null && map.containsKey("openid") && map.containsKey("session_key")) {

            try {
                String token = TokenUtil.createToken(map.get("openid").toString());

                Authentication authentication = new Authentication();
                authentication.setOpenid(map.get("openid").toString());
                authentication.setSession_key(map.get("session_key").toString());

                return JsonUtil.success("用户创建成功", token);
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }

        }

        return JsonUtil.failure("请求微信授权信息失败");
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
    获取用户类型
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/userType")
    public String getUserType(@RequestParam int id) {
        return null;
    }

    /*
    删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE, value = "/userInfo")
//    public boolean deleteUserInfo(@RequestParam int id) {
//        return userService.deleteUserById(id);
//    }

}
