package com.unbxd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unbxd.dao.StudentDao;
import com.unbxd.dao.MongoStudentDaoImpl;
import com.unbxd.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.inject.Inject;

public class StudentServiceImpl implements StudentService{
    private StudentDao crud;

    @Inject
    public StudentServiceImpl(MongoStudentDaoImpl crudops){
        this.crud = crudops;

    }

    @Override
    public Student readCollection(int id) throws JsonProcessingException {

        return crud.readDB(id);
    }

    @Override
    public boolean updateCollection(int id, Student student) {

        return crud.updateDB(id, student);

    }

    @Override
    public boolean deleteCollection (int id){

        return crud.deleteDB(id);

    }

    @Override
    public boolean insetNew(String readData){
        Student student = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            student = objectMapper.readValue(readData, Student.class);
            crud.insetDB(student);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }

    }

}