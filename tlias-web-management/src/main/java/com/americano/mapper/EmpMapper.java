package com.americano.mapper;

import com.americano.pojo.Emp;
import com.americano.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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


    //新增员工基本信息
    @Options(useGeneratedKeys = true, keyProperty = "id") //获取到生成的主键 -- mybatis主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    values(#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}," +
            "           #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    //根据id批量删除员工基本信息 -- 动态sql
    void deleteByIds(List<Integer> ids);

    //根据id查询员工信息以及员工工作经历
    Emp getById(Integer id);

    // 根据id修改员工基本信息
    void updateById(Emp emp);

    // 统计员工职位人数
    @MapKey("pos") // 加不加都可以，防止mybatis假报错
    List<Map<String, Object>> countEmpJobDate();

    // 统计员工性别人数
    @MapKey("name") // 加不加都可以，防止mybatis假报错
    List<Map<String, Object>> countEmpGenderData();

    // 查询全部员工
    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from emp")
    List<Emp> findAll();

    // 根据部门id查询员工数量
    @Select("select count(*) from emp where dept_id = #{id};")
    Integer countByDeptId(Integer id);
}
