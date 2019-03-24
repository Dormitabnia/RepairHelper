package com.ss.rh.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.entity.Order;
import com.ss.rh.entity.OrderExample;
import com.ss.rh.service.MethodReflactService;
import com.ss.rh.service.OrderService;
import com.ss.rh.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BOrderController {

    private static final Logger logger = LoggerFactory.getLogger(BOrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    MethodReflactService methodReflactService;

    /*
    获取报修信息列表
    @param page:获取的页数
    @param size:每页的数量
    @param qt:查询的字段
    @param q:查询的条件
    @param f:查询的报修信息状态
    */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/repairationList")
    public String getOrder(@RequestParam("page") int page, @RequestParam("size") int size,
                           @RequestParam(value = "qt", required = false) String qt,
                           @RequestParam(value = "q", required = false) String q,
                           @RequestParam(value = "f", required = false) String status) {
        PageHelper.startPage(page, size);
        List<Order> orderList;

        try {
            OrderExample orderExample = methodReflactService.getOrderExample(qt, q, status);
            orderList = orderService.getOrdersByExample(orderExample);

        } catch (NoSuchMethodException e) {
            return JsonUtil.failure("method非法字段");

        } catch (NoSuchFieldException e) {
            return JsonUtil.failure("field非法字段");

        } catch (Exception e) {
            return JsonUtil.failure("查找失败");

        }

//        if (qt.isEmpty() && q.isEmpty()) {
//            orderList = orderService.getAllOrders();
//        }
//        else if (qt.equals("id")) {
//            orderList = new ArrayList<>();
//            orderList.add(orderService.getOrderById(Integer.parseInt(q)));
//        }
//        else {
//            try {
//                Class cl = Order.class;
//                Field field = cl.getDeclaredField(qt);
//                String fieldType = field.getGenericType().toString();
//
//                orderList = orderService.getOrdersLike(qt, q, fieldType.equals("class java.lang.String"));
//
//            } catch (NoSuchMethodException e) {
//                return JsonUtil.failure("method非法字段");
//            } catch (NoSuchFieldException e) {
//                return JsonUtil.failure("field非法字段");
//            } catch (Exception e) {
//                return JsonUtil.failure("查找失败");
//            }
//        }

//        for (int i = 0; i < orderList.size(); i++) {
//            if (!orderList.get(i).getStatus().equals(status)) {
//                orderList.remove(i);
//                i--;
//            }
//        }

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
