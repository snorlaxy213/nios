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
                registry.addViewController("/user.html").setViewName("user");
                registry.addViewController("/user-role.html").setViewName("user-role");
                registry.addViewController("/appointment.html").setViewName("appointment");
                registry.addViewController("/diagnosis.html").setViewName("diagnosis");
                registry.addViewController("/registration.html").setViewName("registration");
                registry.addViewController("/drug.html").setViewName("drug");
                registry.addViewController("/401.html").setViewName("401");
                registry.addViewController("/change-password.html").setViewName("change-password");
                registry.addViewController("/dispensing.html").setViewName("dispensing");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new NiosLocaleResolver();
    }
}
