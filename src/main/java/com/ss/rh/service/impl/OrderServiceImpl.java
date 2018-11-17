package com.ss.rh.service.impl;

import com.ss.rh.dao.OrderMapper;
import com.ss.rh.entity.Order;
import com.ss.rh.entity.OrderExample;
import com.ss.rh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
