package com.unbxd;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.unbxd.controller.CRUDController;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.Pippo;

import java.util.Properties;

public class CrudDemo extends ControllerApplication {
    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new CrudModule());
            Properties systemProperties = System.getProperties();
            CRUDController application = injector.getInstance(CRUDController.class);
            Pippo pippo = new Pippo(application);
            pippo.start();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}