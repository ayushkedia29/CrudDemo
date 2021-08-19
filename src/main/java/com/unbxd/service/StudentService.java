package com.unbxd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unbxd.model.Student;

public interface StudentService {

    Student readCollection(int id) throws JsonProcessingException;
    boolean deleteCollection (int id);
    boolean updateCollection(int id, Student student);
    boolean insetNew (String readData);
}
