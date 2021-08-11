package com.unbxd.service;

import com.mongodb.client.MongoCollection;
import com.unbxd.model.Student;

public interface StudentService {
    void insertInTo(MongoCollection collection);

    String deleteCollection (int id);
    String updateCollection(int id, Student student);
    void insetNew (Student student);
}
