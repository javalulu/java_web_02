package com.americano.service;

import com.americano.pojo.JobOption;

public interface ReportService {
    /**
     * 统计员工职位人数
     */
    JobOption getEmpJobData();
}
