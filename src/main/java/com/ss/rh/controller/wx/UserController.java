package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.constants.Constants;
import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.User;
import com.ss.rh.service.AuthenticationService;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.TokenUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import org.slf4j.Logger;

@RestController
public class UserController extends BaseRestController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    TokenUtil tokenUtil;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /*
    获取当前用户信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/userInfo")
    public String getUserInfo() {
        User user = getSessionUser();

        return JsonUtil.success("query success", user);
    }

    /*
    用户登录
     */
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(@RequestParam("code") String code) {
        if (code.isEmpty())
            return JsonUtil.failure("code为空，用户未授权");

        // 通过code获取用户session
        Map map = tokenUtil.getSessionByJsCode(code);

        if (map != null && map.containsKey("openid") && map.containsKey("session_key")) {
            String token = tokenUtil.createToken(map.get("openid").toString());

            // 判断该用户是否已注册
            User qUser = authenticationService.getUserByOpenId(map.get("openid").toString());

            // 若未注册则注册，在数据库中加入用户信息
            if (qUser == null) {
//                qUser = new User();
                qUser.setAuthority(Constants.ORDINARY);  // 默认为普通用户
                userService.insertUser(qUser);

                Authentication authentication = new Authentication();
                authentication.setOpenid(map.get("openid").toString());
                authentication.setSession_key(map.get("session_key").toString());
                authentication.setUserId(qUser.getId());
                authenticationService.insertAuth(authentication);
            }

            saveSession(qUser.getId(), token);
            saveUser(qUser);

            return JsonUtil.success("Login success", token);
        }

        return JsonUtil.failure("微信授权出错");
    }

    /*
    新增用户
     */
//    @RequestMapping(method = RequestMethod.POST, value = "/auth")
//    public String addUser(@RequestParam("code") String code) {
//        if (code.isEmpty())
//            return JsonUtil.failure("code为空，用户未授权");
//
//        //通过code获取用户session
//        Map map = TokenUtil.getSessionByJsCode(code);
//
//
//        if (map != null && map.containsKey("openid") && map.containsKey("session_key")) {
//
//            String token = TokenUtil.createToken(map.get("openid").toString());
//
//            Authentication authentication = new Authentication();
//            authentication.setOpenid(map.get("openid").toString());
//            authentication.setSession_key(map.get("session_key").toString());
//
//            return JsonUtil.success("用户创建成功", token);
//
//        }
//
//        return JsonUtil.failure("请求微信授权信息失败");
//    }

    /*
    修改当前用户信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/userInfo")
    public String modifyUserInfo(@RequestBody User user) {
        User sUser = getSessionUser();
        if ((!sUser.getId().equals(user.getId())) && sUser.getAuthority() != Constants.ADMIN)
            return JsonUtil.failure("无权限修改", 401);
        boolean flag = userService.updateUser(user);

        if (!flag)
            return JsonUtil.failure("修改失败", 500);

        return JsonUtil.success("修改成功");
    }

    /*
    获取当前用户类型
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/userType")
    public String getUserType() {
        int userType = getSessionUser().getAuthority();
        return JsonUtil.success("Query success", userType);
    }

    /*
    删除用户信息
     */
//    @RequestMapping(method = RequestMethod.DELETE, value = "/userInfo")
//    public boolean deleteUserInfo(@RequestParam int id) {
//        return userService.deleteUserById(id);
//    }

}
