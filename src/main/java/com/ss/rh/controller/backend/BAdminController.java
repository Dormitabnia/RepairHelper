package com.ss.rh.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.controller.BaseRestController;
import com.ss.rh.entity.Administrator;
import com.ss.rh.service.AdministratorService;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BAdminController extends BaseRestController {

    private static final Logger logger = LoggerFactory.getLogger(BAdminController.class);

    @Autowired
    AdministratorService administratorService;

    @Autowired
    TokenUtil tokenUtil;

    /*
    后台登录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/backend/login")
    public String bLogin(@RequestBody Map<String, Object> data) {
        String username = (String) data.get("username");
        String password = (String) data.get("password");

        Administrator qAdmin = administratorService.getAdministratorByUserName(username);

        if (qAdmin == null)
            return JsonUtil.failure("Invalid Username", 401);

        if (!qAdmin.getPassword().equals(password)) {
            logger.warn("Invalid Password");
            return JsonUtil.failure("Invalid Password!", 401);
        }

        String token = tokenUtil.createBToken(qAdmin.getId().toString());

        saveSession(qAdmin.getId(), token);

        return JsonUtil.success("login success!", token);
    }

    /*
    后台登出
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/logout")
    public String bLogout() {
        int uid = getSessionUid();

        if (uid == -1)
            JsonUtil.success("already logout");

        deleteAdmin(uid);

        return JsonUtil.success("logout success");
    }

    /*
    查看所有管理员
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/adminList")
    public String getAdminList(@RequestParam("page") int page, @RequestParam("size") int size,
                               @RequestParam(value = "qt", required = false) String qt,
                               @RequestParam(value = "q", required = false) String q) {

        PageHelper.startPage(page, size);

        List<Administrator> adminList;

        if (qt.isEmpty() && q.isEmpty())
            adminList = administratorService.getAdminList();
        else if (qt == "id") {
            adminList = new ArrayList<>();
            adminList.add(administratorService.getAdministratorById(Integer.parseInt(q)));
        }
        else {
            try {
                adminList = administratorService.getAdminsLike(qt, q);
            } catch (NoSuchMethodException e) {
                return JsonUtil.failure("非法字段");
            } catch (Exception e) {
                return JsonUtil.failure("查找失败");
            }
        }

        PageInfo res = new PageInfo(adminList);

        return JsonUtil.success("query success", res);
    }

    /*
    根据id查询管理员信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/backend/admin")
    public String getAdmin(@RequestParam("id") int id) {
        Administrator user = administratorService.getAdministratorById(id);

        if (user == null)
            return JsonUtil.failure("未找到指定用户");

        return JsonUtil.success("query success", user);
    }

    /*
    修改管理员信息
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/backend/admin")
    public String modifyAdmin(@RequestBody Administrator admin) {
        boolean flag = administratorService.updateAdministrator(admin);

        if (!flag)
            return JsonUtil.failure("modify failure", 500);
        else
            return JsonUtil.success("modify success");
    }

    /*
    删除管理员
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.DELETE, value = "/backend/admin")
    public String deleteAdmin(@RequestBody int id) {
        boolean flag = administratorService.deleteAdmin(id);

        if (!flag)
            return JsonUtil.failure("delete failure", 500);
        else
            return JsonUtil.success("delete success");
    }

    /*
    增加管理员
     */
    @BLoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/backend/admin")
    public String addAdmin(@RequestBody Administrator admin) {
        boolean flag = administratorService.insertAdministrator(admin);

        if (!flag)
            return JsonUtil.failure("insert failure", 500);
        else
            return JsonUtil.success("insert success");
    }
}
