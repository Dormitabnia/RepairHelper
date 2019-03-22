package com.ss.rh.service;

import com.ss.rh.entity.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator getAdministratorById(int id);

    boolean insertAdministrator(Administrator administrator);

    boolean updateAdministrator(Administrator administrator);

    Administrator getAdministratorByUserName(String username);

    List<Administrator> getAdminList();

    boolean deleteAdmin(int id);
}
