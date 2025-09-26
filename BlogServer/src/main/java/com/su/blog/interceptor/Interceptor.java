package com.su.blog.interceptor;

import com.su.blog.exception.BaseException;
import com.su.blog.properties.JwtProperties;
import com.su.blog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
public class Interceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    //请求拦截器,校验用户是否登录
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是静态资源,直接放行
        //instanceof检测左边的对象是否是右边的类或实例
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //获取请求头token
        String token = request.getHeader(jwtProperties.getHeader());
        //判断token是否为空
        if (token == null) {
            throw new BaseException("用户未登录");
        }
        //对请求头的token进行校验
        try {
            log.info("token:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecurity(), token);
            Long userId = Long.valueOf(claims.get("UserId").toString());
            log.info("当前用户id：{}", userId);
            //判断是否过期
            if (claims.getExpiration().before(new java.util.Date())) {
                throw new BaseException("用户已过期");
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

    }


}
