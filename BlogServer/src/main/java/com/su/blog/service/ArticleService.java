package com.su.blog.service;


import com.su.blog.dto.ArticleDto;
import com.su.blog.result.PageResult;



public interface ArticleService {
    public PageResult getArticle(ArticleDto articleDto);
}
