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
                .addPathPatterns("/post-category")
                .addPathPatterns("/myPost")
                .addPathPatterns("/myPage")//접속이 불가능하다
                .addPathPatterns("/yourPage")
                .addPathPatterns("/membergroupinput")
                .addPathPatterns("/groupinput")
                .addPathPatterns("/myinfoPage")
                .addPathPatterns("/grupinput")
                .addPathPatterns("/groupjoin")
                .addPathPatterns("/groupjoinmember")
                .addPathPatterns("/groupmy")
                .addPathPatterns("/delgroup")
                .addPathPatterns("/gjdel")
                .addPathPatterns("/delmembergroup")
                .addPathPatterns("/delgroupjoinmember")
                .addPathPatterns("/modno")
                .addPathPatterns("/greport")
                .addPathPatterns("/grouplike")
                .addPathPatterns("/g_memberjoin")
                .addPathPatterns("/gmjoinmod")
                .addPathPatterns("/commentdel")
                .addPathPatterns("/postDeleteComment")
                .addPathPatterns("/postDelete")
                .addPathPatterns("/postMod")
                .excludePathPatterns("/snsMaster")  //가로채지않는다. 접속가능하다
                .excludePathPatterns("/log*")
                .excludePathPatterns("/id*")
                .excludePathPatterns("/pwSearch")
                .excludePathPatterns("/signUp")
                .excludePathPatterns("/email*");


    }
}