package com.su.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * (Articles)实体类
 *
 * @author makejava
 * @since 2025-09-07 21:16:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容(支持长文本)
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 作者
     */
    private String author;
    /**
     * 分类类别
     */
    private String categoryId;
    /**
     * 更新时间
     */
    private Date updateTime;


}

