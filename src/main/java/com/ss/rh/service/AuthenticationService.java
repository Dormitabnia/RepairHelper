package com.ss.rh.service;

import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.User;

public interface AuthenticationService {

    Authentication getAuthByUserId(int uid);

    boolean insertAuth(Authentication authentication);

    User getUserByOpenId(String openid);

}
