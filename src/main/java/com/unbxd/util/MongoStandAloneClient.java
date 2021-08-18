package com.unbxd.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class MongoStandAloneClient {
    private MongoClient mongoClient;

    public MongoStandAloneClient(){
        this.mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://mongo:27017"));
    }

    public com.mongodb.MongoClient getClient(){
        return this.mongoClient;
    }
}



