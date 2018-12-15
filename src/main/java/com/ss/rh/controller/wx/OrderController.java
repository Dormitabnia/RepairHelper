package com.ss.rh.controller.wx;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.constants.Constants;
import com.ss.rh.entity.Order;
import com.ss.rh.entity.OrderPost;
import com.ss.rh.entity.User;
import com.ss.rh.service.OrderService;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController extends BaseRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

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

        return JsonUtil.success("query success", orders);
    }

    /*
    获取某一具体报修信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/repairation")
    public String getOrder(@RequestParam("id") int id) {
        User user = getSessionUser();
        Order order = orderService.getOrderById(id);

        if (user.getAuthority() == Constants.ORDINARY && !order.getUserId().equals(user.getId()))
            return JsonUtil.failure("权限不足！");

        return JsonUtil.success("查询成功", order);

    }

    /*
    创建报修信息
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/repairation")
    public String createOrder(@RequestBody OrderPost orderPost, @RequestAttribute("userId") int userId) {
        Order order = new Order();

        order.setEquipInfo(orderPost.getEquipInfo());
        order.setFaultInfo(orderPost.getFaultInfo());
        order.setUserId(userId);
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
    public String modifyOrderStatus(@RequestParam("rid") int id, @RequestParam("status") String status) {
        User user = getSessionUser();

        int authLevel = user.getAuthority();

        Order q_order = orderService.getOrderById(id);

        if (!hasRight(status, authLevel))
            return JsonUtil.failure("没有权限");

        return orderService.updateOrder(q_order) ? JsonUtil.success("修改成功") : JsonUtil.failure("修改失败");
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
