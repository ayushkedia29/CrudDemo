package com.unbxd.service;

import com.unbxd.model.Student;

public interface StudentService {

    boolean deleteCollection (int id);
    boolean updateCollection(int id, Student student);
    void insetNew (Student student);
}
