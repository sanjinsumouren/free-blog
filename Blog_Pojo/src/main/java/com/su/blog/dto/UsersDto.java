package com.su.blog.dto;

import com.su.blog.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto extends Users {
    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
