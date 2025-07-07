package com.americano.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {
    // 根据clazzId查询班级人数
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);
}
