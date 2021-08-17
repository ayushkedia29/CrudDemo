package com.unbxd.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unbxd.model.Student;

public interface StudentCollections {
    Object readCollection(int id) throws JsonProcessingException;
    boolean updateCollection(int id, Student student);
    boolean deleteCollection(int id);
    void insetNew(Student student);

    void insetInTo();
}
