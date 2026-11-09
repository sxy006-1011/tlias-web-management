package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.studentCountData;
import com.itheima.pojo.studentDegreeData;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper ClazzMapper;

    @Override
    public JobOption getEmpJobData() {

        //调用mapper
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2.组装结果并返回
        List<Object> jobList=list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList=list.stream().map(dataMap->dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<studentDegreeData> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public studentCountData getStudentCountData() {
        List<Map<String, Object>> list = ClazzMapper.countStudentCountData();
        List<String> clazzList = list.stream().map(dataMap-> (String) dataMap.get("clazzList")).toList();
        List<Object> dataList = list.stream().map(dataMap-> (Object) dataMap.get("dataList")).toList();
        return new studentCountData(clazzList, dataList);
    }
}
