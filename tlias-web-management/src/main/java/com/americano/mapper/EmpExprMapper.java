package com.americano.mapper;

import com.americano.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 员工工作经历
@Mapper
public interface EmpExprMapper {
    //批量保存员工工作经历信息
    void insertBatch(List<EmpExpr> exprList);

    //根据员工id, 批量删除员工工作经历信息
    void deleteByEmpIds(List<Integer> empIds);
}
