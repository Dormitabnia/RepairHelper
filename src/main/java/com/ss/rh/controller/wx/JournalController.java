package com.ss.rh.controller.wx;

import com.github.pagehelper.PageHelper;
import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.controller.BaseRestController;
import com.ss.rh.entity.Journal;
import com.ss.rh.entity.User;
import com.ss.rh.service.JournalService;
import com.ss.rh.service.UserService;
import com.ss.rh.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class JournalController extends BaseRestController {
    private final static Logger logger = LoggerFactory.getLogger(JournalController.class);

    @Autowired
    JournalService journalService;

    @Autowired
    UserService userService;

    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/journalList")
    public String getJournals(@RequestParam("id") int rid,
                              @RequestParam("page") int page,
                              @RequestParam("size") int size) {
        User user = getSessionUser();

        if (user.getAuthority() <= 1) {
            PageHelper.startPage(page, size);
            List<Journal> journalList = journalService.getJournalsByRid(rid);
            List<Map<String, Object>> journalRes = new ArrayList<>();
            for (Journal journal : journalList) {
                Map<String, Object> journalMap = JsonUtil.json2Map(JsonUtil.object2JsonStr(journal));

                User repairer = userService.getUserById(journal.getUserId());

                journalMap.put("userName", repairer.getName());

                journalRes.add(journalMap);
            }
            return JsonUtil.success("query success", journalRes);
        }
        else {
            return JsonUtil.failure("没有权限", 401);
        }
    }

    @LoginRequired
    @RequestMapping(method = RequestMethod.GET, value = "/journal")
    public String getJournal(@RequestParam("id") String jid) {
        User user = getSessionUser();

        if (user.getAuthority() <= 1) {

            Journal journal = journalService.getJournalById(Integer.parseInt(jid));

            if (journal != null) {
                Map<String, Object> res = JsonUtil.json2Map(JsonUtil.object2JsonStr(journal));

                User repairer = userService.getUserById(journal.getUserId());

                res.put("userName", repairer.getName());

                return JsonUtil.success("query success", res);
            }

            return JsonUtil.failure("nothing found", 404);
        }

        return JsonUtil.failure("没有权限", 401);
    }

    @LoginRequired
    @RequestMapping(method = RequestMethod.PUT, value = "/journal")
    public String modifyJournal(@RequestBody Journal journal) {
        User user = getSessionUser();

        if (user.getAuthority() <= 1) {
            journal.setUserId(user.getId());
            journal.setCreateTime(new Date());
            boolean flag = journalService.updateJournal(journal);

            if (flag)
                return JsonUtil.success("modify success");
            else
                return JsonUtil.failure("modify failure");
        }

        return JsonUtil.failure("没有权限", 401);
    }

    @LoginRequired
    @RequestMapping(method = RequestMethod.DELETE, value = "/journal")
    public String deleteJournal(@RequestBody Map<String, Object> data) {
        int id = (int) data.get("id");

        boolean flag = journalService.deleteJournalById(id);

        if (!flag)
            return JsonUtil.failure("delete failure", 500);
        else
            return JsonUtil.success("delete success");
    }

    @LoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/journal")
    public String createJournal(@RequestBody Journal journal) {

        if (journal.getOrderId() == null)
            return JsonUtil.failure("维修信息id不能为空");
        if (journal.getContent().isEmpty())
            return JsonUtil.failure("内容不能为空");

        Journal n_journal = new Journal();

        User user = getSessionUser();

        n_journal.setOrderId(journal.getOrderId());
        n_journal.setUserId(user.getId());
        n_journal.setContent(journal.getContent());
        n_journal.setCreateTime(new Date());

        boolean flag = journalService.insertJournal(n_journal);

        if (flag)
            return JsonUtil.success("create success");
        else
            return JsonUtil.failure("create failure");
    }
}
