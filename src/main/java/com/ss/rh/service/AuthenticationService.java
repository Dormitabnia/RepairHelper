package com.ss.rh.service;

import com.ss.rh.entity.Authentication;

public interface AuthenticationService {

    public Authentication getAuthByUserId(int uid);

    public boolean insertAuth(Authentication authentication);
}
