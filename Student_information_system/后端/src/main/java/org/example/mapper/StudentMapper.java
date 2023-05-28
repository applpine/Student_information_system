package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT * FROM student WHERE " +
            "(#{number} IS NULL OR number LIKE CONCAT('%', #{number}, '%')) AND " +
            "(#{name} IS NULL OR name LIKE CONCAT('%', #{name}, '%')) AND " +
            "(#{age} IS NULL OR age = #{age}) AND " +
            "(#{place} IS NULL OR place LIKE CONCAT('%', #{place}, '%'))")
    List<Student> searchByKeywords(@Param("number") String number, @Param("name") String name,
                                   @Param("age") Integer age, @Param("place") String place);

}
