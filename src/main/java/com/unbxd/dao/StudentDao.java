package com.unbxd.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unbxd.model.Student;

public interface StudentDao {
    Student readDB(int id) throws JsonProcessingException;
    boolean updateDB(int id, Student student);
    boolean deleteDB(int id);
    void insetDB(Student student);
}
