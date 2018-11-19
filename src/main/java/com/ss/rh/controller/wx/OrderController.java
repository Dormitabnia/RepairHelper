package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.entity.Order;
import com.ss.rh.entity.User;
import com.ss.rh.service.OrderService;
import com.ss.rh.util.AuthUtil;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/repairationList")
    public List<Order> getOrderList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int uid = 0;

        if(user.getAuthority() == AuthUtil.ORDINARY)
            uid = user.getId();

        return orderService.getOrderListByUid(uid);
    }

    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/repairation")
    public String getOrder(@RequestParam int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Order order = orderService.getOrderById(id);

        if(user.getAuthority() == AuthUtil.ORDINARY && !order.getUserId().equals(user.getId()))
            return JsonUtil.failure("权限不足！");

        return JsonUtil.success("查询成功", order);

    }
}
