package com.americano.service.impl;

import com.americano.mapper.EmpExprMapper;
import com.americano.mapper.EmpMapper;
import com.americano.pojo.*;
import com.americano.service.EmpLogService;
import com.americano.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

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

    @Transactional //事务管理 -- 默认出现运行时异常RunTime Exception才会回滚，使用@Transactional(rollbackFor = {Exception.class})设置所有异常都回滚
    @Override
    public void save(Emp emp) {
        try {
            // 1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 2.保存员工经历信息
            List<EmpExpr> exprList = emp.getExprList();
            //判断是否有工作经历
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工： " + emp);
            empLogService.insertLog(empLog);
        }
    }
}
