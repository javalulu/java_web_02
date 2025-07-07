package com.americano.mapper;

import com.americano.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    // 根据clazzId查询班级人数
    @Select("select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);

    // 条件分页查询
    List<Student> list(String name, Integer degree, Integer clazzId);

    // 添加学员
    @Insert("insert into student(name, no, gender, phone,id_card, is_college, address, degree, graduation_date,clazz_id, create_time, update_time) VALUES " +
            "(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    // 根据ID查询
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    // 修改学员
    void update(Student student);

    // 删除学员
    void delete(List<Integer> ids);

    // 违纪处理
    @Update("update student set violation_count = violation_count + 1 , violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    void violationHandle(Integer id, Integer score);
}
