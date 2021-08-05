package com.unbxd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unbxd.model.Id;
import com.unbxd.model.Student;
import com.unbxd.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;


public class BasicApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);

    CrudService crudService = new CrudService();

    @Override
    protected void onInit() {

        // send 'Hello World' as response


        /*
        GET("/student.*", routeContext -> {
            if (routeContext.getSession("username") == null) {
                routeContext.setSession("originalDestination", routeContext.getRequest().getApplicationUriWithQuery());
                routeContext.redirect("/login");
            } else {
                routeContext.next();
            }
        });


        POST("/login", new RouteHandler() {

            @Override
            public void handle(RouteContext routeContext) {
                String username = routeContext.getParameter("username").toString();
                String password = routeContext.getParameter("password").toString();
                if (authenticate(username, password)) {
                    String originalDestination = routeContext.removeSession("originalDestination");
                    routeContext.resetSession();

                    routeContext.setSession("username", username);
                    routeContext.redirect(originalDestination != null ? originalDestination : "/contacts");
                } else {
                    routeContext.flashError("Authentication failed");
                    routeContext.redirect("/login");
                }
            }

            private boolean authenticate(String username, String password) {
                return !username.isEmpty() && !password.isEmpty();
            }

        });
        */


        PUT("/student", routeContext -> {

            ObjectMapper objectMapper = new ObjectMapper();
            String readData = routeContext.getRequest().getBody();
            Student student = null;
            try {
                student = objectMapper.readValue(readData, Student.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            Boolean validation = crudService.insetNew(student);
            if(validation == false){
                routeContext.flashError("Authentication failed");
            }
        });

        GET("/student/read", routeContext -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String id = routeContext.getRequest().getBody();
            Id id1 = null;
            try {
                id1 = objectMapper.readValue(id, Id.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("id i get issssssssssssssss: " + id1.getId());
            Student student = crudService.readCollection(id1.getId());
            routeContext.json().send(student);
        });

        DELETE("/student/delete", routeContext -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String id = routeContext.getRequest().getBody();
            Id id1 = null;
            try {
                id1 = objectMapper.readValue(id, Id.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("id i get issssssssssssssss: " + id1.getId());
            crudService.deleteCollection(id1.getId());

        });

        POST("/student/update", routeContext -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String id = routeContext.getRequest().getBody();
            Id id1 = null;
            try {
                id1 = objectMapper.readValue(id, Id.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("id i get issssssssssssssss: " + id1.getId());
            crudService.updateCollection(id1.getId());
        });


    }
}
