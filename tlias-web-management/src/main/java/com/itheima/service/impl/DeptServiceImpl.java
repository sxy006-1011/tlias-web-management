package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    public List<Dept> findAll() {
      return deptMapper.findAll();
    }
    @Override
    public void deleteById(Integer id){
        deptMapper.deleteById(id);
    }
    @Override
    public void add(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }
    @Override
    public Dept update(Integer id){
        return deptMapper.update(id);
    }
    @Override
    public void update1(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update1(dept);
    }
}
