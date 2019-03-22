package com.ss.rh.controller.backend;

import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.entity.Authentication;
import com.ss.rh.service.AuthenticationService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String getAuthList() {
        List<Authentication> authList = authenticationService.getAuthList();

        return JsonUtil.success("query success", authList);
    }
}
