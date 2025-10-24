package com.zut.mapper;

import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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

//插入员工数据
    //需要获取数据库自增的主键，并赋值给id
    // useGeneratedKeys：需要主键
    //keyProperty将插入后返回的主键封装到id属性中
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)value "+
    "(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    //批量删除员工信息
    void deleteByIds(List<Integer> ids);

    //根据id查询详细信息
    Emp getById(Integer id);
}

