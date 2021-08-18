package com.unbxd;

import com.google.inject.AbstractModule;
import com.unbxd.controller.CRUDController;
import com.unbxd.service.StudentService;
import com.unbxd.service.StudentServiceImpl;
import com.unbxd.util.MongoStandAloneClient;

public class CrudModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MongoStandAloneClient.class).asEagerSingleton();
        bind(CRUDController.class).asEagerSingleton();
        bind(StudentService.class).to(StudentServiceImpl.class).asEagerSingleton();
    }
}
