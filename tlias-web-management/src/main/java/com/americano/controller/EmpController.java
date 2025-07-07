package com.americano.controller;

import com.americano.pojo.Emp;
import com.americano.pojo.EmpQueryParam;
import com.americano.pojo.PageResult;
import com.americano.pojo.Result;
import com.americano.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //分页查询
/*    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询：{}，{}, {}, {}, {}, {}",  page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/
    //问题：方法行参过多，不利于后续维护 --> 封装为一个对象EmpQueryParam

    // 优化后使用EmpQueryParam的方法
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询：{}",  empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工： {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /*// 删除员工
    @DeleteMapping
    public Result delete(Integer[] ids) { // 直接使用数组来接受前端传递的数据
        log.info("删除员工： {}", Arrays.toString(ids));
        return Result.success();
    }*/

    // 删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) { // 使用集合接收，需要加@RequestParam - 推荐
        log.info("删除员工： {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    // 根据id查询员工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工信息： {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    // 修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工： {}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }
}
