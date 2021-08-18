package com.unbxd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.unbxd.model.Student;
import com.unbxd.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;
import ro.pippo.core.Response;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


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

            ObjectMapper objectMapper = new ObjectMapper();
            String readData = routeContext.getRequest().getBody();
            Student student = null;
            try {
                student = objectMapper.readValue(readData, Student.class);
            } catch (JsonProcessingException e) {
                routeContext.status(400);
                routeContext.send("Invalid Input");
                e.printStackTrace();
            }
            crudService.insetNew(student);

            routeContext.send("New document added successfully");
        });

        GET("/student/read/{id}", routeContext -> {
            int id = routeContext.getParameter("id").toInt();
            Student student = null;
            try {
                student = (Student) crudService.readCollection(id);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if(student==null){
                //
                routeContext.status(400);
                routeContext.send("No Record Found");
            }
            else {
                Student stud = student;
                routeContext.json().send(stud);
            }
        });

        GET("/student/delete/{id}", routeContext -> {

            int id = routeContext.getParameter("id").toInt();

            Boolean response = crudService.deleteCollection(id);
            if(response){
                routeContext.send("Deleted Successfully");
            }else{
                routeContext.status(400);
                routeContext.send("No record found");
            }

        });

        POST("/student/update/{id}", routeContext -> {
            int id = routeContext.getParameter("id").toInt();

            ObjectMapper objectMapper = new ObjectMapper();
            String readData = routeContext.getRequest().getBody();

            Student student = null;

            try {
                student = objectMapper.readValue(readData, Student.class);
            } catch (JsonProcessingException e) {
                routeContext.status(400);
                routeContext.send("Invalid Input");
                e.printStackTrace();
            }

            Boolean response = crudService.updateCollection(id,student);
            if(response){
                routeContext.send("updated Successfully");
            }else{
                routeContext.status(400);
                routeContext.send("No record found");
            }
        });


    }
}
