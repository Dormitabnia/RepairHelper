package com.ss.rh.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.entity.Administrator;
import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.Order;
import com.ss.rh.service.AuthenticationService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BAuthenController {

    @Autowired
    AuthenticationService authenticationService;

    /*
    根据用户id获取微信授权信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/authInfo")
    public String getAuthInfo(@RequestParam("uid") int uid) {
        Authentication qAuth = authenticationService.getAuthByUserId(uid);

        if (qAuth == null)
            return JsonUtil.failure("未找到指定信息");

        return JsonUtil.success("query success", qAuth);
    }

    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/authList")
    public String getAuthList(@RequestParam("page") int page, @RequestParam("size") int size,
                              @RequestParam(value = "qt", required = false) String qt,
                              @RequestParam(value = "q", required = false) String q) {

        PageHelper.startPage(page, size);

        List<Authentication> authList;

        if (qt.isEmpty() && q.isEmpty())
            authList = authenticationService.getAuthList();
        else if (qt == "id") {
            authList = new ArrayList<>();
            authList.add(authenticationService.getAuthById(Integer.parseInt(q)));
        }
        else {
            try {
                Class cl = Administrator.class;
                Field field = cl.getDeclaredField(qt);
                String fieldType = field.getGenericType().toString();

                authList = authenticationService.getAuthsLike(qt, q, fieldType.equals("class java.lang.String"));
            } catch (NoSuchMethodException e) {
                return JsonUtil.failure("非法字段");
            } catch (Exception e) {
                return JsonUtil.failure("查找失败");
            }
        }

        PageInfo res = new PageInfo(authList);

        return JsonUtil.success("query success", res);
    }
}
