package com.su.blog.controller;

import com.su.blog.dto.ArticleDto;
import com.su.blog.result.PageResult;
import com.su.blog.result.Result;
import com.su.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/list")
    public Result<PageResult> getArticle(ArticleDto articleDto) {
        log.info("获取文章参数{}", articleDto);
        return Result.success(articleService.getArticle(articleDto));
    }

}
