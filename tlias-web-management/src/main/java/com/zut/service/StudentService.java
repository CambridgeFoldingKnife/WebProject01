package com.zut.service;

import com.zut.pojo.PagBean;
import com.zut.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PagBean page(StudentQueryParam studentQueryParam);


    void delete(List<Integer> ids);
}
