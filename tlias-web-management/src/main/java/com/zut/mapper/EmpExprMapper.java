package com.zut.mapper;

import com.zut.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /*
      循环查询list集合中的数据。
      动态sql循环查询
     */

    void insertBatch(List<EmpExpr> exprList);

}
