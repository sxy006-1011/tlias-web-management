package com.itheima.mapper;


import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//员工操作经历
@Mapper
public interface EmpExprMapper {



    public void insertBatch(List<EmpExpr> exprList);

    //根据员工id删除工作经历
    void deleteByEmpIds(List<Integer> empIds);
}
