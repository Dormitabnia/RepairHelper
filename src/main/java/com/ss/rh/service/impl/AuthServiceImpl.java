package com.ss.rh.service.impl;

import com.ss.rh.dao.AuthenticationMapper;
import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.AuthenticationExample;
import com.ss.rh.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationMapper authenticationMapper;

    @Override
    public Authentication getAuthByUserId(int uid) {
        return null;
    }

    @Override
    public boolean insertAuth(Authentication authentication) {
        return false;
    }

    @Override
    public Authentication getUserIdByAccessToken(String tk) {
        AuthenticationExample ex = new AuthenticationExample();
        ex.createCriteria().andAccessTokenEqualTo(tk);

        return authenticationMapper.selectByExample(ex).get(0);
    }
}
