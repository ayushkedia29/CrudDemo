package com.unbxd.util;

import com.mongodb.MongoClientURI;


public class MongoClient {
    public com.mongodb.MongoClient getClient(){
        return new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }
}
