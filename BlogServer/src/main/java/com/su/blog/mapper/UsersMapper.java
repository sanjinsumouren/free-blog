package com.su.blog.mapper;

import com.github.pagehelper.Page;
import com.su.blog.dto.UsersDto;
import com.su.blog.entity.Users;
import com.su.blog.enumPackage.OperationEnum;
import com.su.blog.annotation.AutoTime;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UsersMapper {
    //获取用户列表
    Page<Users> getUsersList(UsersDto usersDto);

    //根据用户id查询用户
    Users getUsersById(Integer id);

    //新增用户
    @AutoTime(OperationEnum.INSERT)
    void insertUsers(Users users);

    //删除用户
    void deleteUsersById(Integer id);

    //修改用户
    @AutoTime(OperationEnum.UPDATE)
    void updateUsers(Users users);

    //根据用户名查询
    Users getUsersByName(String userName);
}
