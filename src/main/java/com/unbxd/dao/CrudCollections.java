package com.unbxd.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoClient;
import com.unbxd.model.Student;

public interface CrudCollections {
    Object readCollection(MongoClient mongoClient, int id) throws JsonProcessingException;
    String updateCollection(MongoClient mongoClient, int id, Student student);
    String deleteCollection(MongoClient mongoClient, int id);
    void insetNew(MongoClient mongoClient, Student student);

    void insetInTo(MongoClient mongoClient);
}
