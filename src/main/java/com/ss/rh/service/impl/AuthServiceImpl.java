package com.ss.rh.service.impl;

import com.ss.rh.dao.AuthenticationMapper;
import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.AuthenticationExample;
import com.ss.rh.entity.User;
import com.ss.rh.service.AuthenticationService;
import com.ss.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationMapper authenticationMapper;

    @Autowired
    UserService userService;

    @Override
    public Authentication getAuthByUserId(int uid) {
        return null;
    }

    @Override
    public boolean insertAuth(Authentication authentication) {
        return false;
    }

    @Override
    public User getUserByOpenId(String openid) {
        AuthenticationExample ex = new AuthenticationExample();
        ex.createCriteria().andOpenidEqualTo(openid);
        Authentication auth = authenticationMapper.selectByExample(ex).get(0);

        if (auth == null) return null;

        return userService.getUserById(auth.getUserId());
    }
//    @Override
//    public Authentication getUserIdByAccessToken(String tk) {
//        AuthenticationExample ex = new AuthenticationExample();
//        ex.createCriteria().andAccessTokenEqualTo(tk);
//
//        return authenticationMapper.selectByExample(ex).get(0);
//    }
}
