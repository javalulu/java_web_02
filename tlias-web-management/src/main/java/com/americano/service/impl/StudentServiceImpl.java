package com.americano.service.impl;

import com.americano.mapper.StudentMapper;
import com.americano.pojo.PageResult;
import com.americano.pojo.Student;
import com.americano.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Student> studentList = studentMapper.list(name, degree, clazzId);
        Page<Student>  studentPage = (Page<Student>) studentList;

        return new PageResult(studentPage.getTotal(), studentPage.getResult());
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.insert(student);
    }

    // 根据ID查询
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    // 修改学员
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    // 删除学员
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    // 违纪处理
    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.violationHandle(id, score);
    }
}
