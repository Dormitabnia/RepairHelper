package com.ss.rh.service.impl;

import com.ss.rh.entity.Order;
import com.ss.rh.entity.OrderExample;
import com.ss.rh.entity.User;
import com.ss.rh.entity.UserExample;
import com.ss.rh.service.EntityExampleService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Service
public class EntityExampleServiceImpl implements EntityExampleService {

    @Override
    public OrderExample getOrderExample(String qt, String q, String status) throws Exception {
        OrderExample oe = new OrderExample();

        boolean needSearch = !qt.isEmpty() && !q.isEmpty();
        boolean needFilter = !status.isEmpty();

        // 若搜索字段与条件不为空，则加上select条件
        if (needSearch) {
            String qtname;

            if (!Character.isUpperCase(qt.charAt(0)))
                qtname = new StringBuilder().append(Character.toUpperCase(qt.charAt(0))).append(qt.substring(1)).toString();
            else
                qtname = qt;

            Class cl = OrderExample.Criteria.class;

            Class orderClass = Order.class;
            Field field = orderClass.getDeclaredField(qt);  // throws NoSuchFieldException
            String fieldType = field.getGenericType().toString();

            boolean isString = fieldType.equals("class java.lang.String");

            Method method;

            OrderExample.Criteria criteria = oe.createCriteria();

            if (isString) {
                String qstr = "%" + q + "%";
                method = cl.getMethod("and" + qtname + "Like", String.class);
                method.invoke(criteria, qstr);
            } else {
                method = cl.getMethod("and" + qtname + "EqualTo", Integer.class);
                method.invoke(criteria, Integer.parseInt(q));
            }

        }

        // 若筛选状态不为空
        if (needFilter) {
            oe.createCriteria().andStatusEqualTo(status);
        }

        return oe;
    }

    @Override
    public UserExample getUserExample(String qt, String q, int f) throws Exception {
        UserExample ue = new UserExample();

        boolean needSearch = !qt.isEmpty() && !q.isEmpty();
        boolean needFilter = f != -1;

        // 若搜索字段与条件不为空，则加上select条件
        if (needSearch) {
            String qtname;


            if (!Character.isUpperCase(qt.charAt(0)))
                qtname = new StringBuilder().append(Character.toUpperCase(qt.charAt(0))).append(qt.substring(1)).toString();
            else
                qtname = qt;

            Class cl = UserExample.Criteria.class;

            Class userClass = User.class;
            Field field = userClass.getDeclaredField(qt);  // throws NoSuchFieldException
            String fieldType = field.getGenericType().toString();

            boolean isString = fieldType.equals("class java.lang.String");

            Method method;

            UserExample.Criteria criteria = ue.createCriteria();

            if (isString) {
                String qstr = "%" + q + "%";
                method = cl.getMethod("and" + qtname + "Like", String.class);  // throws NoSuchMethodException
                method.invoke(criteria, qstr);
            } else {
                method = cl.getMethod("and" + qtname + "EqualTo", Integer.class);  // throws NoSuchMethodException
                method.invoke(criteria, Integer.parseInt(q));
            }

        }

        // 若筛选状态不为空
        if (needFilter) {
            ue.createCriteria().andAuthorityEqualTo(f);
        }

        return ue;
    }
}
