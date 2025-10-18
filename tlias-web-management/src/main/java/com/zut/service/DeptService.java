package com.zut.service;

import com.zut.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> list() throws Exception;

    void delete(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
