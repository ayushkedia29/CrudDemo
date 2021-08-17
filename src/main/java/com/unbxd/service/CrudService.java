package com.unbxd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unbxd.dao.StudentCollections;
import com.unbxd.dao.MongoStudentImpl;
import com.unbxd.model.Student;

import javax.inject.Inject;

public class CrudService implements StudentService{
    private StudentCollections crud;
    private com.mongodb.MongoClient mongoClient;

    @Inject
    public CrudService(MongoStudentImpl crudops){
        this.crud = crudops;
        this.crud.insetInTo();
    }


    public Object readCollection(int id) throws JsonProcessingException {

        return crud.readCollection(id);
    }

    @Override
    public boolean updateCollection(int id, Student student) {
        /*
        if (((Object) id).getClass().getSimpleName() != "Integer") {
            return false;
        }

         */
        return crud.updateCollection(id, student);
        //return true;
    }

    @Override
    public boolean deleteCollection (int id){
        /*
        if (((Object) id).getClass().getSimpleName() != "Integer") {
            return false;
        }
         */
        return crud.deleteCollection(id);
        //return true;
    }

    @Override
    public void insetNew(Student student){
        crud.insetNew(student);
        /*
        if (validate(student)) {
            crud.insetNew(mongoClient, student);
            return true;
        } else {
            return false;
        }
        */
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


