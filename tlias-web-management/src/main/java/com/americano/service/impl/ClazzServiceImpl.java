package com.americano.service.impl;

import com.americano.exeption.BusinessException;
import com.americano.mapper.ClazzMapper;
import com.americano.mapper.StudentMapper;
import com.americano.pojo.Clazz;
import com.americano.pojo.PageResult;
import com.americano.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 条件分页查询
     * @param name
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Clazz> dataList = clazzMapper.list(name,begin,end);
        Page<Clazz> p = (Page<Clazz>) dataList;

        return new PageResult(p.getTotal(), p.getResult());
    }

    /**
     * 添加班级
     * @param clazz
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据Id查询班级
     * @param id
     */
    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getInfo(id);
    }

    /**
     * 修改班级
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    /**
     * 删除班级
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        Integer count = studentMapper.countByClazzId(id);
        if (count > 0) {
            throw new BusinessException("班级下有学员, 不能直接删除~");
        }
        clazzMapper.deleteById(id);
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
