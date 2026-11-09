package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.studentCountData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    Page<Clazz> getClazzList(ClazzQueryParam clazzQueryParam);


    @Delete("delete from clazz where id = #{id}")
    void deleteClazz(Integer id);



    void addClazz(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);


    void updateClazz(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> selectAllClazz();


    @Select("select clazz.name clazzList,count(student.id) dataList" +
            " from clazz left join student on clazz.id=student.clazz_id" +
            " group by clazz.id")
    List<Map<String, Object>> countStudentCountData();
}
