package com.zut.service;

import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import com.zut.pojo.PagBean;

import java.util.List;

public interface EmpService  {
    PagBean page(EmpQueryParam queryParam);

    void add(Emp emp);
     //批量删除员工
    void delete(List<Integer> ids);
     //根据id查询员工详细信息
    Emp getInfo(Integer id);

    void update(Emp emp);
//获取所有员工的姓名 在添加班级时选择对应班主任列表
    List<Emp> getEmpList();
}
