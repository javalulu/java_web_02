package com.americano.service;

import com.americano.pojo.Clazz;
import com.americano.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    /**
     * 条件分页查询
     * @param name
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    // 添加班级
    void save(Clazz clazz);

    // 根据Id查询班级
    Clazz getInfo(Integer id);

    // 修改班级
    void update(Clazz clazz);

    // 删除班级
    void deleteById(Integer id);

    // 查询所有班级
    List<Clazz> findAll();

}
