package com.unbxd.dao;

import com.mongodb.client.MongoCollection;
import com.unbxd.model.Student;
import org.bson.Document;

public interface CrudCollections {
    void insetInTo(MongoCollection collection);
    Student readCollection(MongoCollection collection, int id);
    void updateCollection(MongoCollection collection, int id);
    long deleteCollection(MongoCollection collection, int id);
    void insetNew(MongoCollection<Document> collection, Student student);
}
