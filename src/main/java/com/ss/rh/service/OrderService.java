package com.ss.rh.service;

import com.ss.rh.entity.Order;

import java.util.List;

public interface OrderService {

    public Order getOrderById(int id);

    public List<Order> getOrderListByUid(int uid);

    public boolean updateOrder(Order order);

    public boolean insertOrder(Order order);

}
