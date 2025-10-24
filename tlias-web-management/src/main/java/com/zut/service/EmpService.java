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
}
