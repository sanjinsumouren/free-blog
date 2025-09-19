package com.su.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    // 用户id
    private Integer id;
    // 用户名
    private String userName;
    // 密码
    private String userPassword;
    // 邮箱
    private String email;
    // 创建时间
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
