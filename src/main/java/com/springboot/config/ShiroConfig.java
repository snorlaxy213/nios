package com.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * create ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         *      Shiro inside filter :execute the filter about roles
         *          useful filter:
         *              anon: 无需认证（登录）可以访问
         *              authc:需要认证才可以访问
         *              user:如果使用remember可以直接访问
         *              perms：该资源必须得到权限才可以访问
         *              role：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        //-----------------anon
        filterMap.put("/toLogin", "anon");
        filterMap.put("/index.html", "anon");
        filterMap.put("/", "anon");

        //-------------------perms
        filterMap.put("/emps", "perms[user:add]");

        //------------------logout
        filterMap.put("/logout.action", "logout");

        //------------------authc
        filterMap.put("/*", "authc");

        //modify login page
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //set unauthorized page
        shiroFilterFactoryBean.setUnauthorizedUrl("/main.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * create DefaultWebSecurityManger
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //associate with realm
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    /**
     * create realm
     */
    @Bean("shiroRealm")
    public ShiroRealm getShiroRealm(){
        return new ShiroRealm();
    }

    /**
     * set ShiroDialect for mix thymeleaf and shiro to use
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
