package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.constants.Constants;
import com.ss.rh.controller.BaseRestController;
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
    public String login(@RequestParam(value="code") String code, @RequestParam("defaultName") String nickname) {
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
                qUser = new User();
                qUser.setName(nickname);
                qUser.setCompany("未设置");
                qUser.setPhone("未设置");
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
    修改当前用户信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/userInfo", produces = "application/json;charset=utf-8")
    public String modifyUserInfo(@RequestBody Map<String, Object> data) {
        User sUser = getSessionUser();
        logger.info("当前用户:" + sUser.getId());

        if (data.get("user") instanceof Map) {
            Map user = (Map) data.get("user");

            User nUser = JsonUtil.json2Object(JsonUtil.object2JsonStr(user), User.class);

            logger.info(nUser.getCompany());
            logger.info(nUser.getPhone());

            if ((!sUser.getId().equals(nUser.getId())) && sUser.getAuthority() != Constants.ADMIN) {
                logger.warn("用户" + sUser.getId() + "试图越权修改用户信息");
                return JsonUtil.failure("无权限修改", 401);
            }

            saveUser(nUser);

            // 插入数据库
            boolean flag = userService.updateUser(nUser);

            if (!flag)
                return JsonUtil.failure("修改失败", 500);
            else
                return JsonUtil.success("修改成功");
        }

        return JsonUtil.failure("提交的数据类型不正确");
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

}
