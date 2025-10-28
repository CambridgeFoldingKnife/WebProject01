package com.zut.service;

import com.zut.pojo.Clazz;
import com.zut.pojo.ClazzQueryParam;
import com.zut.pojo.PagBean;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClazzService {
    PagBean page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    List<Clazz> getClazzList();
}
