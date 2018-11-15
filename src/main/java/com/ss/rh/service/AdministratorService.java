package com.ss.rh.service;

import com.ss.rh.entity.Administrator;

public interface AdministratorService {

    public Administrator getAdministratorById(int id);

    public boolean insertAdministrator(Administrator administrator);

    public boolean updateAdministrator(Administrator administrator);
}
