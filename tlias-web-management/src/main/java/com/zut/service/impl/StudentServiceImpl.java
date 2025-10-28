package com.zut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.mapper.StudentMapper;
import com.zut.pojo.PagBean;
import com.zut.pojo.Student;
import com.zut.pojo.StudentQueryParam;
import com.zut.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
     private StudentMapper studentMapper;
    //学生列表的分页查询
    @Override
    public PagBean page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        List<Student> studentList = studentMapper.list(studentQueryParam);

        Page<Student> s = (Page<Student>) studentList;

        return new PagBean(s.getTotal(),s.getResult());
    }
//批量删除删除学生

    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }
}
