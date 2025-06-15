package com.americano.mapper;

import com.americano.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    // 查询所有部门数据

    // 因数据库里的命名与java对象的命名不一致，导致mybatis无法自动封装，所以需要：
    // 1. 手动结果映射
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    // 2. 起别名
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc;")

    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    // 3. 推荐： 开启mybatis驼峰命名，全局应用 -> application.yml

    // 根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    // 新增部门
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    // 根据id查询部门数据
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    // 修改部门数据
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
