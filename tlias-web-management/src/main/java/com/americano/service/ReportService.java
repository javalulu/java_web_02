package com.americano.service;

import com.americano.pojo.JobOption;
import com.americano.pojo.ClazzCountOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> getEmpGenderData();

    // 班级人数统计
    ClazzCountOption getStudentData();

    // 统计学员的学历信息
    List<Map> getStudentDegreeData();
}
