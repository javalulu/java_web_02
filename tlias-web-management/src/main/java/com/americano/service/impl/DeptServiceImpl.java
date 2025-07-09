package com.americano.service.impl;

import com.americano.exeption.BusinessException;
import com.americano.mapper.DeptMapper;
import com.americano.mapper.EmpMapper;
import com.americano.pojo.Dept;
import com.americano.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        Integer count = empMapper.countByDeptId(id);
        if (count > 0) {
            throw new BusinessException("部门下有员工， 不能删除");
        }

        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        // 补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        //调用Mapper
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        // 补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        //调用Mapper
        deptMapper.update(dept);
    }
}
