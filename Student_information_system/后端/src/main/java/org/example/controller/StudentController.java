package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import lombok.val;
import org.example.mapper.StudentMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Student;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//响应所有请求
@CrossOrigin(origins = { "*" , "null" })
//不显示错误
@SuppressWarnings("all")
@RestController
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    private  Gson gson = new Gson();
    @GetMapping("/select")
    public String selectList()
    {
        List<Student> studentList = studentMapper.selectList(null);
        return gson.toJson(studentList);
    }
    @PostMapping("delete")
    public void delete(@RequestBody Student student){
        studentMapper.deleteById(student);
    }
    @PostMapping("update")
    public void update( @RequestBody Student student){
        studentMapper.updateById(student);
    }
    @PostMapping("/add")
    public void add(@RequestBody Student student){
        studentMapper.insert(student);
    }
    // 根据姓名模糊查询学生
    @PostMapping("search")
    public String search(@RequestBody Student student){
//        QueryWrapper<Student>studentQueryWrapper = new QueryWrapper<>();
//        studentQueryWrapper.like("name",student.getName());
        String number = student.getNumber();
        String name = student.getName();
        String place = student.getPlace();
        int age1 = student.getAge();
        Integer age = (age1 != 0) ? Integer.valueOf(age1) : null;

        if(number==null && name==null && age==null && place==null)
        {
            return selectList();
        }
        List<Student> studentList = studentMapper.searchByKeywords(number,name,age,place);
        return gson.toJson(studentList);
    }
}
