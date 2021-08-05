package com.unbxd.service;

import com.mongodb.client.MongoCollection;
import com.unbxd.dao.CrudCollections;
import com.unbxd.model.Student;
import com.unbxd.util.MongoClient;

public class CrudService {
    private CrudCollections crud;
    MongoClient mongo = new MongoClient();
    public MongoCollection collection = mongo.mongoClient();

    public void insertInTo(MongoCollection collection) {
        crud.insetInTo(collection);
    }

    public Student readCollection(int id) {
        return crud.readCollection(collection, id);
    }

    public boolean updateCollection(int id) {
        if (((Object) id).getClass().getSimpleName() != "Integer") {
            return false;
        }
        crud.updateCollection(collection, id);
        return true;
    }

    public boolean deleteCollection ( int id){
        if (((Object) id).getClass().getSimpleName() != "Integer") {
            return false;
        }
        crud.deleteCollection(collection,id);
        return true;
    }


    public boolean insetNew (Student student){
        if (validate(student)) {
            crud.insetNew(collection, student);
            return true;
        } else {
            return false;
        }
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


