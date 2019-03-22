package com.ss.rh.service.impl;

import com.ss.rh.dao.AuthenticationMapper;
import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.AuthenticationExample;
import com.ss.rh.entity.User;
import com.ss.rh.service.AuthenticationService;
import com.ss.rh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationMapper authenticationMapper;

    @Autowired
    UserService userService;

    @Override
    public Authentication getAuthByUserId(int uid) {
        AuthenticationExample ex = new AuthenticationExample();
        ex.createCriteria().andUserIdEqualTo(uid);

        List<Authentication> authList = authenticationMapper.selectByExample(ex);

        if (authList.size() == 0)
            return null;

        return authList.get(0);
    }

    @Override
    public boolean insertAuth(Authentication authentication) {
        return authenticationMapper.insert(authentication) > 0;
    }

    @Override
    public User getUserByOpenId(String openid) {
        AuthenticationExample ex = new AuthenticationExample();
        ex.createCriteria().andOpenidEqualTo(openid);

        List<Authentication> authenticationList = authenticationMapper.selectByExample(ex);

        if (authenticationList.size() == 0) return null;
        Authentication auth = authenticationList.get(0);

        return userService.getUserById(auth.getUserId());
    }

    @Override
    public List<Authentication> getAuthList() {
        AuthenticationExample ex = new AuthenticationExample();

        return authenticationMapper.selectByExample(ex);
    }
}
