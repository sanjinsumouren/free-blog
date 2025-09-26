package com.su.blog.config;

import com.su.blog.interceptor.Interceptor;
import com.su.blog.json.JsonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration

public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private Interceptor interceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }

    //扩展mvc框架的消息转换器
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建一个消息转换器对象(固定写法)
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器,可以将Java对象转换成json字符串(提前创建好一个所需要用到的JacksonObjectMapper),将对象放入刚创建好的转换器对象
        converter.setObjectMapper(new JsonObjectMapper());
        //将自己的转换器放入MVC的容器中,设置索引为第一个
        converters.add(0, converter);
    }
}
