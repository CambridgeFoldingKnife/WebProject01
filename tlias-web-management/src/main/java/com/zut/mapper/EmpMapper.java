package com.zut.mapper;

import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    //获取总数据数
   /*  @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    Long count();*/



     //显示指定页面的数据列表
   /* @Select("select emp.*,dept.name  deptName from emp left join dept  on emp.dept_id = dept.id limit #{start},#{pageSize}")
    List<Emp> list(Integer start,Integer pageSize);*/


    //显示指定页面的数据列表
   /* @Select("select emp.*,dept.name  deptName from emp left join dept  on emp.dept_id = dept.id ")
   */
    List<Emp> list(EmpQueryParam queryParam);

}

