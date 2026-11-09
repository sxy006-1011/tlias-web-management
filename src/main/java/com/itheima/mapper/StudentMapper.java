package com.itheima.mapper;


import com.github.pagehelper.Page;
import com.itheima.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    Page<Student> selectByPage(StudentQueryParam studentQueryParam);



    void deleteById(List<Integer> ids);


    void addStudent(Student student);


@Select("SELECT * FROM student WHERE id = #{id}")
    Student selectStudentById(Integer id);

    void update(Student student);

    @Update("UPDATE student SET violation_score = violation_score + #{score},violation_count=violation_count+1 WHERE id = #{id}")
    void violationDealt(Integer id, Integer score);

    @Select ("select (case when degree=1 then ' 初中 ' when degree=2 then ' 高中 '" +
            " when degree=3 then ' 大专 ' when degree=4 then ' 本科 ' when degree=5 then ' 硕士 ' " +
            "when degree=6 then " +
            "' 博士 ' else ' 未知 ' end) as name, count(*) as value from student group by degree")
    List<studentDegreeData> countStudentDegreeData();


}
