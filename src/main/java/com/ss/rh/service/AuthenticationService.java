package com.ss.rh.service;

import com.ss.rh.entity.Authentication;

public interface AuthenticationService {

    Authentication getAuthByUserId(int uid);

    boolean insertAuth(Authentication authentication);

    Authentication getUserIdByAccessToken(String tk);
}
