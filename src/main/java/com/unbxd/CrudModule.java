package com.unbxd;

import com.google.inject.AbstractModule;
import com.unbxd.controller.BasicApplication;
import com.unbxd.util.MongoClient;

public class CrudModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MongoClient.class).asEagerSingleton();
        bind(BasicApplication.class).asEagerSingleton();
    }
}
