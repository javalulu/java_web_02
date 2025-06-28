package com.americano.service;

import com.americano.pojo.Emp;
import com.americano.pojo.EmpQueryParam;
import com.americano.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface EmpService {
    // 分页查询
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    // 分页查询-优化
    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
