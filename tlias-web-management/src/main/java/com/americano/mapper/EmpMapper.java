package com.americano.mapper;

import com.americano.pojo.Emp;
import com.americano.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

// 员工信息
@Mapper
public interface EmpMapper {
    // -------------原始方法------------
    // 查询总记录数
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//
//    // 分页查询
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //-----------使用PageHelper插件方法------------
    // 注意： 1. 使用PageHelper时sql语句最后不能加分号
    //       2. PageHelper只对PageHelper.startPage后面紧跟的一条查询生效，其他的不生效
    // 实现机制：将一条sql语句通过拼接count(0)和limit...，输出两条sql语句
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    //-----------使用PageHelper插件方法------------优化
    public List<Emp> list(EmpQueryParam empQueryParam);

}
