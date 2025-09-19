package com.su.blog.service;

import com.su.blog.dto.UsersDto;
import com.su.blog.entity.Users;
import com.su.blog.result.PageResult;
import com.su.blog.vo.UserLoginVo;

public interface UserService {

    PageResult list(UsersDto usersDto);

    //根据id查询用户
    Users getUsersById(Integer id);

    //添加用户
    void insertUsers(Users users);

    //删除用户
    void deleteUsersById(Integer Id);

    //更新用户密码
    void updateUsers(Users users);

    UserLoginVo userLogin(Users user);
}
