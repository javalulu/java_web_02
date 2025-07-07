package com.americano.service;

import com.americano.pojo.PageResult;
import com.americano.pojo.Student;

import java.util.List;

public interface StudentService {
    // 条件分页查询
    PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    // 添加学员
    void save(Student student);

    // 根据ID查询
    Student getInfo(Integer id);

    // 修改学员
    void update(Student student);

    // 删除学员
    void delete(List<Integer> ids);

    // 违纪处理
    void violationHandle(Integer id, Integer score);
}
