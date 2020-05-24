package com.example.demo.config;


import com.example.demo.intercepter.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Rade
 * @Date 2019-10-29 10:50
 * 说明：
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    //在此处，将拦截器注册为一个 Bean

    /**
     * 这里有个小坑，记录一下（注册成Bean才能Autowired）
     * https://blog.csdn.net/SwordSnapLiu/article/details/79665861
     *
     * @return
     */

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor()).addPathPatterns("/**");
    }
}
