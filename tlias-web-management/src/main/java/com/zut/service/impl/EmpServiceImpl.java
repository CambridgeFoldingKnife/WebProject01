package com.zut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.mapper.EmpExprMapper;
import com.zut.mapper.EmpMapper;
import com.zut.pojo.*;
import com.zut.service.EmpLogService;
import com.zut.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
   @Autowired
   private EmpExprMapper empExprMapper;
    @Autowired
   private EmpLogService empLogService;
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
    @Transactional(rollbackFor = {Exception.class}) //事务管理spring注解
    @Override
    public void add(Emp emp){
    try{
            //1.empMapper员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.调用empExprMapper员工经历信息
            //经历表中需要emp_id。但该字段只有在成功插入后数据库自增出现。这里要在mapper中添加获取
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());//设置了每一个empid
                });
            }
            //批量插入经历
            empExprMapper.insertBatch(exprList);
            System.out.println("===========:" + exprList);
        }finally{
            //3.记录新增员工操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),emp.toString());
            empLogService.insertLog(empLog);
        }
    }
   @Transactional
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }
}
