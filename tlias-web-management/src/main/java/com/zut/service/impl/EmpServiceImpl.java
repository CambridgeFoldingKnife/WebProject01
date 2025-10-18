package com.zut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.mapper.EmpMapper;
import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import com.zut.pojo.PagBean;
import com.zut.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /*@Override
    public PagBean page(Integer page, Integer pageSize) {
        //1.获取mapper总记录数total
       Long total =  empMapper.count();

        //2.获取mapper结果列表 rows
        //page是页码  这里计算需要传参需要的起始索引
       Integer start = (page -1)*pageSize;
       List<Emp> rows =  empMapper.list(start,pageSize);

        //3.封装结果
        return new PagBean(total,rows);
    }*/

    @Override
    public PagBean page(EmpQueryParam queryParam) {
        //1.设置分页参数 pageHelper
        PageHelper.startPage(queryParam.getPage(),queryParam.getPageSize());

        //2.执行mapper 查询list
        List<Emp> empList = empMapper.list(queryParam);

        //3.解析结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PagBean(p.getTotal(),p.getResult());
    }

//    新增员工
    @Override
    public void add(Emp emp) {
        //1.empMapper员工基本信息



        //2.调用empExprMapper员工经历信息
    }
}
