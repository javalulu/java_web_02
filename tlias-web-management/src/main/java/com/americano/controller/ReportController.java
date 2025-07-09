package com.americano.controller;

import com.americano.pojo.JobOption;
import com.americano.pojo.Result;
import com.americano.pojo.ClazzCountOption;
import com.americano.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别人数
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("studentCountData")
    public Result getStudentDate() {
        ClazzCountOption clazzCountOption = reportService.getStudentData();
        return Result.success(clazzCountOption);
    }

    /**
     * 学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        List<Map> dataList = reportService.getStudentDegreeData();
        return Result.success(dataList);
    }
}
