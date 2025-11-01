package com.zut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zut.mapper.EmpExprMapper;
import com.zut.mapper.EmpMapper;
import com.zut.pojo.*;
import com.zut.service.EmpLogService;
import com.zut.service.EmpService;
import com.zut.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

//显示员工列表
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


    /*
      查询员工列表用于新增班级时的班主任选择。
      重名的班主任显示对应的员工id 用以区分
     */
    @Override
    public List<Emp> getEmpList() {
        // 1. 查询所有员工（原始数据，包含id和name）
        List<Emp> empList = empMapper.selectList();
        if (empList == null || empList.isEmpty()) {
            return empList; // 空列表直接返回
        }
        // 2. 统计每个姓名的出现次数，判断是否重名
        Map<String, Integer> nameCountMap = new HashMap<>();
        for (Emp emp : empList) {
            String name = emp.getName();
            // 统计每个姓名出现的次数（忽略null或空字符串，避免异常）
            if (name != null && !name.trim().isEmpty()) {
                nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
            }
        }
        // 3. 对重名的员工，在姓名后拼接ID（格式：姓名(id)）
        for (Emp emp : empList) {
            String name = emp.getName();
            // 仅处理非空姓名，且该姓名出现次数>1（即重名）的情况
            if (name != null && !name.trim().isEmpty() && nameCountMap.getOrDefault(name, 0) > 1) {
                // 拼接后的姓名：原姓名 + (id)
                emp.setName(name + "(" + emp.getId() + ")");
            }
            // 不重名的员工，姓名保持不变
        }
        return empList;
    }

    //    新增员工
    @Transactional(rollbackFor = {Exception.class}) //事务管理spring注解
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

    //根据员工id 查询回显全部个人信息
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }


//    修改员工信息
    @Transactional
    @Override
    public void update(Emp emp) {
        //1.修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        //2.根据员工id删除工作经历信息
        //只传递一个员工id
         empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3。添加员工的工作经历信息
        //如若新增员工工作经历信息，需要再此获得该员工id
        List<EmpExpr> exprList = emp.getExprList();
        //判断集合非空
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }


    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.根据前端输入的用户名密码查询员工表
      Emp e = empMapper.getByUsernameAndPassword(emp);

        //2.判断
        //得到的员工信息不为空就返回一个新的登陆结果
        if (e != null){
            String jwt = JwtUtils.generateJwt(Map.of("id", e.getId(), "username", e.getUsername(), "name", e.getName()));
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }//错误返回null
        return null;
    }
}
