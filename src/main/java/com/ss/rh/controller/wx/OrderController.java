package com.ss.rh.controller.wx;

import com.ss.rh.entity.Order;
import com.ss.rh.entity.User;
import com.ss.rh.service.OrderService;
import com.ss.rh.util.AuthUtil;
import com.ss.rh.util.MyMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/repairationList")
    public List<Order> getOrderList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int uid = 0;

        if(user.getAuthority() == AuthUtil.ORDINARY)
            uid = user.getId();

        return orderService.getOrderListByUid(uid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/repairation")
    public MyMsg getOrder(HttpSession session) {
        User user = (User) session.getAttribute("user");

//        if(user.getAuthority() == AuthUtil.ORDINARY)
//            return new MyMsg(false, "权限不足！");

        return new MyMsg(false, "权限不足！");
    }
}
