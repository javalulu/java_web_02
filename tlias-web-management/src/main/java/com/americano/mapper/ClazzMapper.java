package com.americano.mapper;

import com.americano.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.service.annotation.DeleteExchange;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    // 条件分页查询
    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    // 添加班级
    @Insert("insert into clazz values (null, #{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    // 根据Id查询班级
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getInfo(Integer id);

    // 修改班级
    void update(Clazz clazz);

    // 删除班级
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);
}
