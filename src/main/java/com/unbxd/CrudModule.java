package com.unbxd;

import com.google.inject.AbstractModule;
import com.unbxd.controller.CRUDController;
import com.unbxd.util.MongoClient;

public class CrudModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MongoClient.class).asEagerSingleton();
        bind(CRUDController.class).asEagerSingleton();
    }
}
