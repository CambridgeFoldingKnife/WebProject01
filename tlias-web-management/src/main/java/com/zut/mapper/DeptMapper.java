package com.zut.mapper;

import com.zut.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

  /* 数据封装，字段名与参数名不一致可选用该方式
  @Results({
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })*/


  //可引入mybatis 注解严格按照 java驼峰命名  数据库下划线命名
 /*   @Select("select  id, name, create_time ,update_time from dept")*/
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

      @Insert("insert into  dept (name, create_time, update_time) value (#{name},#{createTime},#{updateTime})")
     void insert(Dept dept);

      @Select("select id,name,create_time,update_time from dept where id = #{id}")
       Dept getById(Integer id);

     // @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
     void update(Dept dept);
}
