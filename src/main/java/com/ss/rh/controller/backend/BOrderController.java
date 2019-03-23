package com.ss.rh.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.entity.Order;
import com.ss.rh.service.OrderService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BOrderController {

    @Autowired
    OrderService orderService;

    /*
    获取报修信息列表
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/repairationList")
    public String getOrder(@RequestParam("page") int page, @RequestParam("size") int size,
                           @RequestParam(value = "qt", required = false) String qt,
                           @RequestParam(value = "q", required = false) String q) {
        PageHelper.startPage(page, size);
        List<Order> orderList;

        if (qt.isEmpty() && q.isEmpty())
            orderList = orderService.getAllOrders();
        else if (qt == "id") {
            orderList = new ArrayList<>();
            orderList.add(orderService.getOrderById(Integer.parseInt(q)));
        }
        else {
            try {
                Class cl = Order.class;
                Field field = cl.getDeclaredField(qt);
                Class decalringCl = field.getDeclaringClass();

                orderList = orderService.getOrdersLike(qt, q, decalringCl == String.class);

            } catch (NoSuchMethodException e) {
                return JsonUtil.failure("非法字段");
            } catch (NoSuchFieldException e) {
                return JsonUtil.failure("非法字段");
            } catch (Exception e) {
                return JsonUtil.failure("查找失败");
            }
        }

        PageInfo res = new PageInfo(orderList);

        return JsonUtil.success("query success", res);
    }

    /*
    根据id获取报修信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/repairation")
    public String getOrder(@RequestParam("id") int id) {
        Order order = orderService.getOrderById(id);

        if (order == null)
            return JsonUtil.failure("未找到指定维修信息");

        return JsonUtil.success("query success", order);
    }

    /*
    修改报修信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/backend/repairation")
    public String modifyOrder(@RequestBody Order order) {
        boolean flag = orderService.updateOrder(order);

        if (!flag)
            return JsonUtil.failure("modify failure", 500);
        else
            return JsonUtil.success("modify success");
    }

    /*
    删除报修信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.DELETE, value = "/backend/repairation")
    public String deleteOrder(@RequestBody Map<String, Object> data) {
        int id = (int) data.get("id");

        boolean flag = orderService.deleteOrder(id);

        if (!flag)
            return JsonUtil.failure("delete failure", 500);
        else
            return JsonUtil.success("delete success");
    }

    /*
    增加报修信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/backend/repairation")
    public String addOrder(@RequestBody Order order) {
        boolean flag = orderService.insertOrder(order);

        if (!flag)
            return JsonUtil.failure("insert failure", 500);
        else
            return JsonUtil.success("insert success");
    }
}
