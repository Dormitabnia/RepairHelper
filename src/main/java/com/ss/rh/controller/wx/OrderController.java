package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.constants.Constants;
import com.ss.rh.entity.Order;
import com.ss.rh.entity.OrderPost;
import com.ss.rh.entity.User;
import com.ss.rh.service.OrderService;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController extends BaseRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /*
    获取报修信息列表
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/repairationList")
    public String getOrderList() {
        User user = getSessionUser();
        int uid = 0;

        if (user.getAuthority() == Constants.ORDINARY)
            uid = user.getId();

        List<Order> orders = orderService.getOrderListByUid(uid);

        List<Map<String, Object>> results = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> orderMap = JsonUtil.json2Map(JsonUtil.object2JsonStr(order));

            // 根据id获取维修员姓名
            if (order.getRepairId() != null) {
                String repairer = userService.getUserById(order.getRepairId()).getName();
                orderMap.put("repairer", repairer);
            }
            else
                orderMap.put("repairer", "未分配");
            String submitter = userService.getUserById(order.getUserId()).getName();
            orderMap.put("submitter", submitter);

            results.add(orderMap);
        }

        return JsonUtil.success("query success", orders);
    }

    /*
    获取某一具体报修信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/repairation")
    public String getOrder(@RequestParam(value="id") int id) {
        User user = getSessionUser();
        Order order = orderService.getOrderById(id);

        Map<String, Object> result = JsonUtil.json2Map(JsonUtil.object2JsonStr(order));

        // 根据id获取维修员姓名
        if (order.getRepairId() != null) {
            String repairer = userService.getUserById(order.getRepairId()).getName();
            result.put("repairer", repairer);
        }
        else
            result.put("repairer", "未分配");
        String submitter = userService.getUserById(order.getUserId()).getName();
        result.put("submitter", submitter);

        if (user.getAuthority() == Constants.ORDINARY && !order.getUserId().equals(user.getId()))
            return JsonUtil.failure("权限不足！");

        return JsonUtil.success("查询成功", result);

    }

    /*
    创建报修信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/repairation")
    public String createOrder(@RequestBody Order orderPost) {
//        Map orderMap = (Map)data.get("order");
//        Order orderPost = JsonUtil.json2Object(JsonUtil.object2JsonStr(orderMap), Order.class);

        Order order = new Order();
        User user = getSessionUser();

        order.setEquipInfo(orderPost.getEquipInfo());
        order.setFaultInfo(orderPost.getFaultInfo());
        order.setUserId(user.getId());
        order.setSound(orderPost.getSound());
        order.setImg(orderPost.getImg());
        order.setStatus("报修等待");

        orderService.insertOrder(order);

        return JsonUtil.success("报修信息创建成功");
    }

    /*
    修改报修状态
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/repairation")
    public String modifyOrderStatus(@RequestBody Map<String, Object> data) {
        int id = Integer.parseInt((String) data.get("id"));
        String status = (String) data.get("status");
        logger.info(Integer.toString(id));
        logger.info(status);

        User user = getSessionUser();

        int authLevel = user.getAuthority();

        Order q_order = orderService.getOrderById(id);

        if (!hasRight(status, authLevel)) {
            logger.warn("用户" + user.getId() + "试图越权修改报修信息");
            return JsonUtil.failure("没有权限");
        }
        else {
            if (q_order.getRepairId() == null) {
                q_order.setRepairId(user.getId());
            }
            q_order.setStatus(status);
            return orderService.updateOrder(q_order) ? JsonUtil.success("修改成功") : JsonUtil.failure("修改失败");
        }
    }

    /*
    判断是否有改变状态的权限
     */
    private static boolean hasRight(String status, int auth) {
        boolean flag = false;

        if (status.equals("报修确认") && auth <= Constants.ORDERER)
            flag = true;
        else if ((status.equals("维修处理") || status.equals("处理完毕")) && auth <= Constants.REPAIRER)
            flag = true;

        return flag;
    }
}
