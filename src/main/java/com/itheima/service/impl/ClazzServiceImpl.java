package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> getClazzList(ClazzQueryParam clazzQueryParam) {
      //1.设置分页
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
      //2.执行查询
        Page<Clazz> page = clazzMapper.getClazzList(clazzQueryParam);
      //3.补全参数
        LocalDate now=LocalDate.now();
        page.forEach(clazz -> {
          if(now.isAfter(clazz.getEndDate()))
              clazz.setStatus("已结课");
          else if (now.isBefore(clazz.getBeginDate()))
              clazz.setStatus("未开班");
          else
              clazz.setStatus("在读");
        });
        //4.返回结果
        return new PageResult<>(page.getTotal(), page.getResult());
    }


   @Override
    public void deleteClazz(Integer id) {
        clazzMapper.deleteClazz(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        //补全参数
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }
    @Override
    public Clazz selectClazzById(Integer id) {
        return clazzMapper.selectById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }
    @Override
    public List<Clazz> selectAllClazz() {
        return clazzMapper.selectAllClazz();
    }
}
