package com.su.blog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.su.blog.dto.UsersDto;
import com.su.blog.entity.Users;
import com.su.blog.exception.BaseException;
import com.su.blog.properties.JwtProperties;
import com.su.blog.result.PageResult;
import com.su.blog.utils.JwtUtil;
import com.su.blog.vo.UserLoginVo;
import com.su.blog.mapper.UsersMapper;
import com.su.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UsersMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;

    // 用户列表
    @Override
    public PageResult list(UsersDto usersDto) {
        PageHelper.startPage(usersDto.getPageNum(), usersDto.getPageSize());
        Page<Users> page = userMapper.getUsersList(usersDto);
        List<Users> result = page.getResult();
        long total = page.getTotal();
        return new PageResult(total, result);
    }

    //根据id查询用户
    @Override
    public Users getUsersById(Integer Id) {
        return userMapper.getUsersById(Id);
    }

    //添加用户
    @Override
    public void insertUsers(Users users) {
        //判断用户是否存在
        UsersDto usersDto = new UsersDto();
        usersDto.setUserName(users.getUserName());
        PageResult list = list(usersDto);
        if (list.getTotal() > 0) {
            throw new BaseException("用户已存在,请换一个用户名");
        }

        String userPassword = users.getUserPassword();
        //md5加密
        users.setUserPassword(DigestUtils.md5DigestAsHex(userPassword.getBytes()));
        userMapper.insertUsers(users);
    }
    //删除用户
    @Override
    public void deleteUsersById(Integer Id) {
        userMapper.deleteUsersById(Id);
    }
    //更新用户
    @Override
    public void updateUsers(Users users) {
        String userPassword = users.getUserPassword();
        //md5加密
        users.setUserPassword(DigestUtils.md5DigestAsHex(userPassword.getBytes()));
        userMapper.updateUsers(users);
    }


    //用户登录
    @Override
    public UserLoginVo userLogin(Users users) {
        //根据传递的用户名和密码进行匹配
        Users user = userMapper.getUsersByName(users.getUserName());
        String s = DigestUtils.md5DigestAsHex(users.getUserPassword().getBytes());
        if(!user.getUserPassword().equals(s)){
            throw new BaseException( "密码错误");
        }
        //生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getId());
        String token = JwtUtil.createJwt(jwtProperties.getSecurity(), jwtProperties.getExpiration(), claims);
        return new UserLoginVo(user.getId(), user.getUserName(), token);
    }
}
