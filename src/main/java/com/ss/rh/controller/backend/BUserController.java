package com.ss.rh.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.entity.User;
import com.ss.rh.service.AdministratorService;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BUserController {
    private static final Logger logger = LoggerFactory.getLogger(BUserController.class);

    @Autowired
    AdministratorService administratorService;

    @Autowired
    UserService userService;

    @Autowired
    TokenUtil tokenUtil;

    /*
    查看所有用户信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/userList")
    public String getUserList(@RequestParam("page") int page, @RequestParam("size") int size,
                              @RequestParam(value = "qt", required = false) String qt,
                              @RequestParam(value = "q", required = false) String q) {

        PageHelper.startPage(page, size);

        List<User> userList;

        logger.info(qt);

        if (qt.isEmpty() && q.isEmpty())
            userList = userService.getUserList();
        else if (qt == "id") {
            userList = new ArrayList<>();
            userList.add(userService.getUserById(Integer.parseInt(q)));
        }
        else {
            try {
                userList = userService.getUsersLike(qt, q);
            } catch (NoSuchMethodException e) {
                return JsonUtil.failure("非法字段");
            } catch (Exception e) {
                return JsonUtil.failure("查找失败");
            }
        }

        PageInfo res = new PageInfo(userList);

        return JsonUtil.success("query success", res);
    }

    /*
    根据id查看用户信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/userInfo")
    public String getUserInfo(@RequestParam("id") int id) {
        User user = userService.getUserById(id);

        if (user == null)
            return JsonUtil.failure("未找到指定用户");

        return JsonUtil.success("query success", user);
    }

    /*
    修改用户信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/backend/userInfo")
    public String modifyUserInfo(@RequestBody User user) {
        boolean flag = userService.updateUser(user);

        if (!flag)
            return JsonUtil.failure("modify failure", 500);
        else
            return JsonUtil.success("modify success");
    }

    /*
    删除用户
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.DELETE, value = "/backend/userInfo")
    public String deleteUser(@RequestBody int id) {
        boolean flag = userService.deleteUserById(id);

        if (!flag)
            return JsonUtil.failure("delete failure", 500);
        else
            return JsonUtil.success("delete success");
    }
}
