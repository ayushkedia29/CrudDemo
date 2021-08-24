package com.unbxd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.unbxd.model.Student;
import com.unbxd.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;

public class CRUDController extends Application {

    private static final Logger log = LoggerFactory.getLogger(CRUDController.class);

    private final StudentServiceImpl crudService;

    @Inject
    public CRUDController(StudentServiceImpl cdService){
        this.crudService = cdService;
    }

    @Override
    protected void onInit() {

        POST("/student", routeContext -> {

            String readData = routeContext.getRequest().getBody();

            boolean response = false;
            try{
                response = crudService.insetNew(readData);
            }catch(Exception e){
                routeContext.status(500);
                routeContext.send("Internal Error");
                e.printStackTrace();
            }

            if(response==true){
                routeContext.send("New document added successfully");
            }
            else{
                routeContext.status(400);
                routeContext.send("Invalid document");
            }


        });

        GET("/student/read/{id}", routeContext -> {
            int id = routeContext.getParameter("id").toInt();
            Student student = null;
            try {
                student = crudService.readCollection(id);
            } catch (Exception e) {
                routeContext.status(500);
                routeContext.send("Internal Error");
                e.printStackTrace();
            }
            if(student==null){

                routeContext.send("No Record Found");
            }
            else {
                routeContext.json().send(student);
            }
        });

        GET("/student/delete/{id}", routeContext -> {

            int id = routeContext.getParameter("id").toInt();
            boolean response = false;
            try{
                response = crudService.deleteCollection(id);
            }catch(Exception e){
                routeContext.status(500);
                routeContext.send("Internal Error");
                e.printStackTrace();
            }

            if(response){
                routeContext.send("Deleted Successfully");
            }else{
                routeContext.status(400);
                routeContext.send("No record found");
            }

        });

        POST("/student/update/{id}", routeContext -> {
            int id = routeContext.getParameter("id").toInt();

            String readData = routeContext.getRequest().getBody();

            boolean response = false;
            try{
                response = crudService.updateCollection(id,readData);
            }catch(Exception e){
                routeContext.status(500);
                routeContext.send("Internal Error");
                e.printStackTrace();
            }

            if(response){
                routeContext.send("updated Successfully");
            }else{
                routeContext.status(400);
                routeContext.send("No record found or Invalid Input");

            }
        });


    }
}