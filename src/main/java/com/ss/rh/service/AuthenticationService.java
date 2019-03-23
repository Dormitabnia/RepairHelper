package com.ss.rh.service;

import com.ss.rh.entity.Authentication;
import com.ss.rh.entity.User;

import java.util.List;

public interface AuthenticationService {

    Authentication getAuthByUserId(int uid);

    boolean insertAuth(Authentication authentication);

    User getUserByOpenId(String openid);

    List<Authentication> getAuthList();

    List<Authentication> getAuthsLike(String qt, String q, boolean isString) throws Exception;

    Authentication getAuthById(int id);

}
