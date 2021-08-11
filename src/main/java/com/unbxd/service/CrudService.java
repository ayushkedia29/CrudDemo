package com.unbxd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.MongoCollection;
import com.unbxd.dao.CrudCollections;
import com.unbxd.dao.CrudCollectionsImpl;
import com.unbxd.model.Student;
import com.unbxd.util.MongoClient;

import javax.inject.Inject;

public class CrudService implements StudentService{
    private CrudCollections crud = new CrudCollectionsImpl();
    private com.mongodb.MongoClient mongoClient;

    @Inject
    public CrudService(MongoClient mgClient){
        this.mongoClient = mgClient.getClient();
        this.crud.insetInTo(this.mongoClient);
    }

    @Override
    public void insertInTo(MongoCollection collection) {
        crud.insetInTo(mongoClient);
    }

    public Object readCollection(int id) throws JsonProcessingException {

        return crud.readCollection(mongoClient, id);
    }

    @Override
    public String updateCollection(int id, Student student) {
        /*
        if (((Object) id).getClass().getSimpleName() != "Integer") {
            return false;
        }

         */
        return crud.updateCollection(mongoClient, id, student);
        //return true;
    }

    @Override
    public String deleteCollection (int id){
        /*
        if (((Object) id).getClass().getSimpleName() != "Integer") {
            return false;
        }
         */
        return crud.deleteCollection(mongoClient,id);
        //return true;
    }

    @Override
    public void insetNew(Student student){
        crud.insetNew(mongoClient, student);
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


