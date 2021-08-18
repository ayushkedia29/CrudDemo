package com.unbxd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unbxd.dao.StudentDao;
import com.unbxd.dao.MongoStudentDaoImpl;
import com.unbxd.model.Student;

import javax.inject.Inject;

public class StudentServiceImpl implements StudentService{
    private StudentDao crud;
    private com.mongodb.MongoClient mongoClient;

    @Inject
    public StudentServiceImpl(MongoStudentDaoImpl crudops){

        this.crud = crudops;

    }


    public Object readCollection(int id) throws JsonProcessingException {

        return crud.readCollection(id);
    }

    @Override
    public boolean updateCollection(int id, Student student) {

        return crud.updateCollection(id, student);

    }

    @Override
    public boolean deleteCollection (int id){

        return crud.deleteCollection(id);

    }

    @Override
    public void insetNew(Student student){
        crud.insetNew(student);

    }

    public boolean validate (Student student){
        if (((Object) student.getId()).getClass().getSimpleName() != "Integer") {
            return false;
        } else if (student.getLastname().getClass().getSimpleName() != "String") {
            return false;
        } else if (student.getFirstname().getClass().getSimpleName() != "String") {
            return false;
        } else if (((Object) student.getAge()).getClass().getSimpleName() != "Integer") {
            return false;
        }
        return true;
    }
}


