package com.unbxd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.unbxd.model.Student;
import com.unbxd.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;


public class BasicApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);

    private final CrudService crudService;

    @Inject
    public BasicApplication(CrudService cdService){
        this.crudService = cdService;
    }

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


        POST("/student", routeContext -> {

            ObjectMapper objectMapper = new ObjectMapper();
            String readData = routeContext.getRequest().getBody();
            System.out.println(readData);
            Student student = null;
            try {
                student = objectMapper.readValue(readData, Student.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            boolean validation = crudService.insetNew(student);
            if(!validation){
                routeContext.flashError("Authentication failed");
            }
        });

        GET("/student/read/{id}", routeContext -> {
            int id = routeContext.getParameter("id").toInt();

            Object student = null;
            try {
                student = crudService.readCollection(id);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if(student instanceof String){
                String stud = (String) student;
                routeContext.send(stud);
            }
            else {
                Student stud = (Student) student;
                routeContext.json().send(student);
            }
        });

        DELETE("/student/delete/{id}", routeContext -> {

            int id = routeContext.getParameter("id").toInt();

            System.out.println("id i get issssssssssssssss: " + id);
            crudService.deleteCollection(id);

        });

        GET("/student/update/{id}", routeContext -> {
            System.out.println("hellloooo");
            int id = routeContext.getParameter("id").toInt();
            System.out.println("id i get issssssssssssssss: " + id);
            crudService.updateCollection(id);
            routeContext.send("Updated Successfully");
        });


    }
}
