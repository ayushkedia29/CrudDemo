package com.unbxd;
import com.unbxd.controller.BasicApplication;
import ro.pippo.core.Pippo;

public class CrudDemo {
    public static void main(String[] args) {
        Pippo pippo = new Pippo(new BasicApplication());
        pippo.start();
    }
}