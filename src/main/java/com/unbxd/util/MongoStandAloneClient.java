package com.unbxd.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoStandAloneClient {
    private MongoClient mongoClient;

    public MongoStandAloneClient(){
        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }

    public MongoClient getClient(){
        return this.mongoClient;
    }
}



