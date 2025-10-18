package com.zut.mapper;

import com.zut.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper//生命该接口为mybatis接口，并且运行时自动生成的实现类对象()交给IOC管理
public interface UserMapper {
    /*@Select("select * from user")*/
    public List<User> findAll();
}
