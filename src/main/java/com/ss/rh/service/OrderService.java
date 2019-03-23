package com.ss.rh.service;

import com.ss.rh.entity.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(int id);

    List<Order> getOrderListByUid(int uid);

    boolean updateOrder(Order order);

    boolean insertOrder(Order order);

    List<Order> getAllOrders();

    boolean deleteOrder(int id);

    List<Order> getOrdersLike(String qt, String q) throws Exception;

}
