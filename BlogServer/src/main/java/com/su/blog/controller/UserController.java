package com.su.blog.controller;

import com.su.blog.dto.UsersDto;
import com.su.blog.entity.Users;
import com.su.blog.result.PageResult;
import com.su.blog.result.Result;
import com.su.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    //用户模块
    //获取用户列表
    @GetMapping("/list")
    public Result<PageResult> list(UsersDto usersDto) {
        log.info("获取用户列表参数:{},id:{}", usersDto, usersDto.getId());
        return Result.success(userService.list(usersDto));
    }

    //新增用户
    @PostMapping
    public Result insert(@RequestBody Users users) {
        log.info("新增用户:{}", users);
        userService.insertUsers(users);
        return Result.success();
    }

    //删除用户
    @DeleteMapping("/{id}")
    public Result delete(@RequestParam("id") Integer id) {
        log.info("删除用户:{}", id);
        userService.deleteUsersById(id);
        return Result.success();
    }

    //修改用户
    @PutMapping
    public Result update(@RequestBody Users users) {
        log.info("修改用户:{}", users);
        userService.updateUsers(users);
        return Result.success();
    }

}
