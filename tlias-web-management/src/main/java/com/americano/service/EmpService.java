package com.americano.service;

import com.americano.pojo.Emp;
import com.americano.pojo.EmpQueryParam;
import com.americano.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // 分页查询
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    // 分页查询-优化
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //新增员工信息
    void save(Emp emp);

    //批量删除员工
    void delete(List<Integer> ids);

    //根据id查询员工信息
    Emp getInfo(Integer id);

    //修改员工
    void update(Emp emp);

    //查询全部员工
    List<Emp> list();
}
