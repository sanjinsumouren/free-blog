package com.su.blog.controller;

import com.su.blog.entity.Users;
import com.su.blog.result.Result;
import com.su.blog.vo.UserLoginVo;
import com.su.blog.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    //登录模块
    @PostMapping
    public Result<UserLoginVo> login (@RequestBody Users user){
        log.info("用户登录{}", user);
        return Result.success(userService.userLogin(user));
    }

    @GetMapping()
    public String getUserLoginInfo(){
        return "test";
    }
}
