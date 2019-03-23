package com.ss.rh.service.impl;

import com.ss.rh.dao.OrderMapper;
import com.ss.rh.entity.Order;
import com.ss.rh.entity.OrderExample;
import com.ss.rh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Order getOrderById(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> getOrderListByUid(int uid) {
        OrderExample ex = new OrderExample();
        List<Order> orderList;

        if(uid != 0)
            ex.createCriteria().andUserIdEqualTo(uid);

        orderList = orderMapper.selectByExample(ex);

        return orderList;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateByPrimaryKey(order) > 0;
    }

    @Override
    public boolean insertOrder(Order order) {
        return orderMapper.insert(order) > 0;
    }

    @Override
    public List<Order> getAllOrders() {
        OrderExample ex = new OrderExample();

        return orderMapper.selectByExample(ex);
    }

    @Override
    public boolean deleteOrder(int id) {
        return orderMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<Order> getOrdersLike(String qt, String q) throws Exception {
        String qtname;
        String qq = "%" + q + "%";

        if(!Character.isUpperCase(qt.charAt(0)))
            qtname = new StringBuilder().append(Character.toUpperCase(qt.charAt(0))).append(qt.substring(1)).toString();
        else
            qtname = qt;

        OrderExample ue = new OrderExample();

        Class cl = OrderExample.Criteria.class;

        Method method = cl.getMethod("and" + qtname + "Like", String.class);

        method.invoke(ue.createCriteria(), qq);

        return orderMapper.selectByExample(ue);
    }
}
