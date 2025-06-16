package com.americano.controller;

import com.americano.pojo.Dept;
import com.americano.pojo.Result;
import com.americano.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    // Logback固定常量 (使用lombok注释@Slf4j可省略）
    // private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    // 查询全部部门数据
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list() {
//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    // 删除部门
    // 方式1： 基于HttpServletRequest来获取前端传递的参数 （不推荐，接收到的都是String类型，需要手动转换）
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据部门id删除部门： " + id);
//        return Result.success();
//    }

    // 方式2： @RequestParam直接绑定传递的参数到方法行参
    // 注意： 一旦声明@RequestParam。该参数请求时必须传递，不传递会报错 （默认required为true)
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
//        System.out.println("根据部门id删除部门： " + deptId);
//        return Result.success();
//    }

    // 方式3： 如果前端请求参数与服务端方法行参名字一样，可以省略@RequestParam (推荐）
    @DeleteMapping
    public Result delete(Integer id) {
//        System.out.println("根据部门id删除部门： " + id);
        log.info("根据部门id删除部门：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    // 新增部门
    // 通过@RequestBody将前端的json请求数据封装到行参
    @PostMapping
    public Result add(@RequestBody Dept dept) {
//        System.out.println("新增部门: " + dept);
        log.info("新增部门: {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    // 根据id查询部门
    // 通过@PathVariable来获取前端路径参数
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId) {
//        System.out.println("根据id查询部门： " + deptId);
//        return Result.success();
//    }

    // 路径参数和行参名字一样的话可省略@PathVariable后面的
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
//        System.out.println("根据id查询部门： " + id);
        log.info("根据id查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    // 修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept) {
//        System.out.println("修改部门： " + dept);
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
