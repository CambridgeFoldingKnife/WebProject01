package com.zut.mapper;

import com.zut.pojo.Dept;
import com.zut.pojo.Emp;
import com.zut.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    //根据id更新员工信息
    void update(Emp emp);

//    查询每个职位对应的人数
    @MapKey("pos")//可以不写
    public List<Map> getJobData();
    @MapKey("name")
    public List<Map> getGenderDataMaps();

    //获取所有员工列表，添加班级时选择班主任
    List<Emp> selectList();

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}

