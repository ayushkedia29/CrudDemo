package com.unbxd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.MongoCollection;

public interface StudentService {
    void insertInTo(MongoCollection collection);
    Object readCollection(int id) throws JsonProcessingException;
    boolean updateCollection(int id);
}
