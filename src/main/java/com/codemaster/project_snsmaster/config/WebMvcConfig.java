package com.codemaster.project_snsmaster.config;

import com.codemaster.project_snsmaster.interceptor.LoggerInterceptor;
import com.codemaster.project_snsmaster.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/img/**", "/js/**");


        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/myPage")//접속이 불가능하다
                .addPathPatterns("/myPost")
                .excludePathPatterns("/snsMaster")  //가로채지않는다. 접속가능하다
                .excludePathPatterns("/log*")
                .excludePathPatterns("/id*")
                .excludePathPatterns("/pwSearch")
                .excludePathPatterns("/signUp")
                .excludePathPatterns("/email*")
                .excludePathPatterns("/updateProfile");


    }
}