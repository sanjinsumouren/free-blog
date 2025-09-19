package com.su.blog.service.impl;

import com.su.blog.dto.ArticleDto;
import com.su.blog.entity.Articles;
import com.su.blog.result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.su.blog.mapper.ArticleMapper;
import com.su.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceimpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    //获取文章
    public PageResult getArticle(ArticleDto articleDto){
        PageHelper.startPage(articleDto.getPageNum(),articleDto.getPageSize());
        Page<Articles> page = articleMapper.getArticle(articleDto);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
