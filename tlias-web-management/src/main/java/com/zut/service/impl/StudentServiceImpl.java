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

import java.time.LocalDateTime;
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
//    添加学生

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }
//根据id查询学生信息页面回显
    @Override
    public Student getInfo(Integer id) {
        Student student = studentMapper.getById(id);
        return student;
    }
//修改学生信息
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }
    //学生扣分

    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.Violation(id,score);
    }
}
