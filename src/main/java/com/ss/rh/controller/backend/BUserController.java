package com.ss.rh.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.entity.User;
import com.ss.rh.entity.UserExample;
import com.ss.rh.service.AdministratorService;
import com.ss.rh.service.EntityExampleService;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BUserController {
    private static final Logger logger = LoggerFactory.getLogger(BUserController.class);

    @Autowired
    UserService userService;

    @Autowired
    EntityExampleService entityExampleService;

    /*
    查看所有用户信息
    @param page:获取的页数
    @param size:每页的数量
    @param qt:查询的字段
    @param q:查询的条件
    @param f:查询的用户类型
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/userList")
    public String getUserList(@RequestParam("page") int page, @RequestParam("size") int size,
                              @RequestParam(value = "qt", required = false) String qt,
                              @RequestParam(value = "q", required = false) String q,
                              @RequestParam(value = "f", required = false) String f) {
        PageHelper.startPage(page, size);

        List<User> userList;

        int intF;
        if (f.isEmpty())
            intF = -1;
        else
            intF = Integer.parseInt(f);

        try {
            UserExample ue = entityExampleService.getUserExample(qt, q, intF);
            userList = userService.getUsersByExample(ue);

        } catch (NoSuchFieldException e) {
            return JsonUtil.failure("Field非法字段");

        } catch (NoSuchMethodException e) {
            return JsonUtil.failure("Method非法字段");

        } catch (Exception e) {
            return JsonUtil.failure("查找失败");

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
    public String deleteUser(@RequestBody Map<String, Object> data) {
        int id = (int) data.get("id");

        boolean flag = userService.deleteUserById(id);

        if (!flag)
            return JsonUtil.failure("delete failure", 500);
        else
            return JsonUtil.success("delete success");
    }

    /*
    增加用户
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/backend/userInfo")
    public String addUser(@RequestBody User user) {
        if (user.getCompany() == null)
            user.setCompany("未设置");
        if (user.getPhone() == null)
            user.setPhone("未设置");

        boolean flag = userService.insertUser(user);

        if (flag)
            return JsonUtil.success("insert success");
        else
            return JsonUtil.failure("insert failure");
    }
}
