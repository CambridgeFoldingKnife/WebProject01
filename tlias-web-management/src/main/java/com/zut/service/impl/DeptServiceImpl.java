package com.zut.service.impl;

import com.zut.mapper.DeptMapper;
import com.zut.pojo.Dept;
import com.zut.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
   @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() throws Exception {
        return  deptMapper.findAll();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        //基础属性赋值 创建时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
       //调用mapper
        deptMapper.insert(dept);
    }
    @Override
    public Dept getById(Integer id){
        return   deptMapper.getById(id);
    }
    @Override
    public void update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
