package com.fei.search.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: MovieConfigurerAdapter
 * @Author chengfei
 * @Date 2020/11/10 14:52
 * @Description: TODO
 **/
@Component
public class MovieConfigurerAdapter implements WebMvcConfigurer {

    //注册一个拦截器
    @Bean
    public LoginConfigurerInterceptor loginInterceptor(){
        return new LoginConfigurerInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * @Description: 添加拦截器
      * @param registry
     * @return void
     * @date: 2020/11/10 15:14
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.loginInterceptor()).addPathPatterns("/movie/*");
    }
}
