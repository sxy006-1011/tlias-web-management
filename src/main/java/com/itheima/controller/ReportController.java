package com.itheima.controller;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.pojo.studentCountData;
import com.itheima.pojo.studentDegreeData;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController  {
    @Autowired
    private ReportService reportService;
//统计员工职位人数
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }


@GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
}

@GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历人数");
        List<studentDegreeData> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计学生人数");
        studentCountData list = reportService.getStudentCountData();
        return Result.success(list);
    }



}




