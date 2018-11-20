package com.ss.rh.service;

import com.ss.rh.entity.Administrator;

public interface AdministratorService {

    Administrator getAdministratorById(int id);

    boolean insertAdministrator(Administrator administrator);

    boolean updateAdministrator(Administrator administrator);
}
