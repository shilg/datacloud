package com.fpcloud.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 */
@Configuration
public class DataWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }

    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/toLogin") //登录页
            .excludePathPatterns("/loginCheck") //用户登录
            .excludePathPatterns("/toRegister") //用户注册toRegister
            .excludePathPatterns("/register") //用户注册
            .excludePathPatterns("/toIndex") //首页
            .excludePathPatterns("/toHyIndex") //首页
            .excludePathPatterns("/telnant/toLiveMsgEdit") //客户留言
            .excludePathPatterns("/telnant/saveLiveMsg") //客户留言
            .excludePathPatterns("/verfyCode") //验证码
            .excludePathPatterns("/excelCompar/downloadResult"); //验证码

        super.addInterceptors(registry);
    }
}