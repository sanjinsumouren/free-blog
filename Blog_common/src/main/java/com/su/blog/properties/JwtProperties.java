package com.su.blog.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "blog.jwt")
@Data
public class JwtProperties {
    //密钥
    private String security;
    //过期时间
    private long expiration;
    //存储token的名称
    private String header;
}
