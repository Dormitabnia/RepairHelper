package com.ss.rh.service.impl;

import com.ss.rh.dao.AdministratorMapper;
import com.ss.rh.entity.Administrator;
import com.ss.rh.entity.AdministratorExample;
import com.ss.rh.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class AdminServiceImpl implements AdministratorService {

    @Autowired
    AdministratorMapper administratorMapper;

    @Override
    public Administrator getAdministratorById(int id) {
        return administratorMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insertAdministrator(Administrator administrator) {
        return administratorMapper.insert(administrator) > 0;
    }

    @Override
    public boolean updateAdministrator(Administrator administrator) {
        return administratorMapper.updateByPrimaryKey(administrator) > 0;
    }

    @Override
    public Administrator getAdministratorByUserName(String username) {
        AdministratorExample ae = new AdministratorExample();

        ae.createCriteria().andUsernameEqualTo(username);

        List<Administrator> ads = administratorMapper.selectByExample(ae);

        if (ads.size() == 0)
            return null;

        return ads.get(0);
    }

    @Override
    public List<Administrator> getAdminList() {
        AdministratorExample ae = new AdministratorExample();

        return administratorMapper.selectByExample(ae);
    }

    @Override
    public boolean deleteAdmin(int id) {
        return administratorMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public List<Administrator> getAdminsLike(String qt, String q, boolean isString) throws Exception {
        String qtname;
        String qq = "%" + q + "%";

        if(!Character.isUpperCase(qt.charAt(0)))
            qtname = new StringBuilder().append(Character.toUpperCase(qt.charAt(0))).append(qt.substring(1)).toString();
        else
            qtname = qt;

        AdministratorExample ue = new AdministratorExample();

        Class cl = AdministratorExample.Criteria.class;

        Method method;

        if (isString)
            method = cl.getMethod("and" + qtname + "Like", String.class);
        else
            method = cl.getMethod("and" + qtname + "EqualTo", String.class);

        method.invoke(ue.createCriteria(), qq);

        return administratorMapper.selectByExample(ue);
    }
}
