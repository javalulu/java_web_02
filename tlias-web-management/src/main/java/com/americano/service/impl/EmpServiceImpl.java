package com.americano.service.impl;

import com.americano.mapper.EmpMapper;
import com.americano.pojo.Emp;
import com.americano.pojo.EmpQueryParam;
import com.americano.pojo.PageResult;
import com.americano.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    // --------------原始分页查询--------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        // 1.调用mapper接口，查询总记录数
//        Long total = empMapper.count();
//
//        // 2.调用mapper接口，查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        //3.封装结果PageResult
//        return new PageResult<Emp>(total, rows);
//    }

    // -------------使用PageHelper的分页查询-------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        // 1.设置分页参数(PageHelper)
//        PageHelper.startPage(page, pageSize);
//
//        // 2.执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//
//        //3.解析结果，并封装
//        Page<Emp> p = (Page<Emp>) empList; // List<Emp>强转为Page<emp>
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }

    // -------------使用PageHelper的分页查询-------------优化
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1.设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3.解析结果，并封装
        Page<Emp> p = (Page<Emp>) empList; // List<Emp>强转为Page<emp>
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Emp emp) {
        // 1.保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 2.保存员工经历信息
    }
}
