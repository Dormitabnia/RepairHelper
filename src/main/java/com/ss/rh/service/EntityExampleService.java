package com.ss.rh.service;

import com.ss.rh.entity.OrderExample;
import com.ss.rh.entity.UserExample;

public interface EntityExampleService {

    OrderExample getOrderExample(String qt, String q, String status) throws Exception;

    UserExample getUserExample(String qt, String q, int f) throws Exception;

}
