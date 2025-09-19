package com.su.blog.dto;

import com.su.blog.entity.Articles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto extends Articles {
    //分页参数默认值
    private Integer pageNum = 1;
    //分页参数默认值
    private Integer pageSize = 10;
}
