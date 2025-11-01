package com.zut.service;

import com.zut.pojo.PagBean;
import com.zut.pojo.Student;
import com.zut.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PagBean page(StudentQueryParam studentQueryParam);


    void delete(List<Integer> ids);

    void add(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Integer score);
}
