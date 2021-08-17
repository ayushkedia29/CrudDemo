package com.unbxd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.unbxd.model.Student;
import com.unbxd.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;


public class CRUDController extends Application {

    private static final Logger log = LoggerFactory.getLogger(CRUDController.class);

    private final CrudService crudService;

    @Inject
    public CRUDController(CrudService cdService){
        this.crudService = cdService;
    }

    @Override
    protected void onInit() {

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
            crudService.insetNew(student);
            /*
            if(!validation){
                routeContext.flashError("Authentication failed");
            }
            */
            routeContext.send("New document added successfully");
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

        GET("/student/delete/{id}", routeContext -> {

            int id = routeContext.getParameter("id").toInt();

            System.out.println("id i get issssssssssssssss: " + id);
            String response = crudService.deleteCollection(id);
            routeContext.send(response);
        });

        POST("/student/update/{id}", routeContext -> {
            System.out.println("hellloooo");
            int id = routeContext.getParameter("id").toInt();

            ObjectMapper objectMapper = new ObjectMapper();
            String readData = routeContext.getRequest().getBody();
            System.out.println(readData);
            Student student = null;

            try {
                student = objectMapper.readValue(readData, Student.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("id i get issssssssssssssss: " + id);
            String response = crudService.updateCollection(id,student);
            routeContext.send(response);
        });


    }
}