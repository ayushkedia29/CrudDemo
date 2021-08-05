package com.unbxd;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.unbxd.controller.BasicApplication;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.Pippo;

public class CrudDemo extends ControllerApplication {
    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new CrudModule());
            BasicApplication application = injector.getInstance(BasicApplication.class);
            Pippo pippo = new Pippo(application);
            pippo.start();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}