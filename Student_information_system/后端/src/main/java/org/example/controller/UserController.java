package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
//不显示错误
@SuppressWarnings("all")
@RestController
public class UserController {
    Gson gson = new Gson();
    @Autowired
    private UserMapper userMapper;

    @PostMapping("login")
    public String login(@RequestBody User user){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);
        User user_selected = userMapper.selectOne(userQueryWrapper);
        if(user_selected == null)
        {
            return "0";
        }
        else
        {
            return "1";
        }
    }
    @PostMapping("register")
    public void register(@RequestBody User user){
        userMapper.insert(user);
    }
}
