package com.su.blog.mapper;

import com.su.blog.dto.ArticleDto;
import com.su.blog.entity.Articles;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    Page<Articles> getArticle(ArticleDto articlesDto);
}
