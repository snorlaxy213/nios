package com.springboot.config;

import com.springboot.component.NiosLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/license.html").setViewName("license");
                registry.addViewController("/blank.html").setViewName("blank");
                registry.addViewController("/user.html").setViewName("user");
            }

            /*@Override
            public void addInterceptors(InterceptorRegistry registry) {
                //已经做好静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");
            }*/
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new NiosLocaleResolver();
    }
}
