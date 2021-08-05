package com.unbxd.util;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.unbxd.service.CrudService;

public class MongoClient {
    public MongoCollection mongoClient() {
        MongoCollection Collection = null;
        try {
            com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            MongoDatabase database = mongoClient.getDatabase("db1");
            Collection = database.getCollection("Student_Collection");
            CrudService crudService = new CrudService();
            crudService.insertInTo(Collection);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collection;
    }
}
