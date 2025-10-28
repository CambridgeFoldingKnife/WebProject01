package com.zut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.mapper.ClazzMapper;
import com.zut.pojo.Clazz;
import com.zut.pojo.ClazzQueryParam;
import com.zut.pojo.PagBean;
import com.zut.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PagBean page(ClazzQueryParam clazzQueryParam) {
        //1，设置分页参数 pageHelper
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //执行mapper
        List<Clazz> clazzList =clazzMapper.list(clazzQueryParam);
        //解析结果返回列表
        Page<Clazz> c = (Page<Clazz>) clazzList;
        return new PagBean(c.getTotal(),c.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }
//更新班级信息
    @Override
    public void update(Clazz clazz) {
    clazz.setUpdateTime(LocalDateTime.now());
    clazzMapper.update(clazz);
    }

    //查询单个班级信息--页面回显 用于修改更新操作
    @Override
    public Clazz getInfo(Integer id) {
       return clazzMapper.getById(id);
    }

    @Override
    @Transactional
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
         clazzMapper.insert(clazz);
    }

    @Override
    public List<Clazz> getClazzList() {
        List<Clazz> clazzList = clazzMapper.getClazzList();
        return clazzList;
    }
}
