package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {

   PageResult<Clazz> getClazzList(ClazzQueryParam clazzQueryParam);

   void deleteClazz(Integer id);

   void addClazz(Clazz clazz);

   Clazz selectClazzById(Integer id);

   void updateClazz(Clazz clazz);

   List<Clazz> selectAllClazz();
}
