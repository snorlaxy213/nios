package com.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.springboot.commons.JWTFilter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*@Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }*/

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> jwtFilterMap = new HashMap<>();
        jwtFilterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(jwtFilterMap);

        /**
         *      Shiro inside filter :execute the filter about roles
         *          useful filter:
         *              anon: 无需认证（登录）可以访问
         *              authc:需要认证才可以访问
         *              user:如果使用remember可以直接访问
         *              perms：该资源必须得到权限才可以访问
         *              roles：该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();

        ///-----------------jwt
        filterMap.put("/user-role.html", "jwt");

        //-----------------anon
        filterMap.put("/toLogin", "anon");
        filterMap.put("/index.html", "anon");
        filterMap.put("/", "anon");

        //-------------------role
        filterMap.put("/user.html", "roles[ROL0001]");

        //------------------logout
        filterMap.put("/logout.action", "logout");

        //------------------authc
        filterMap.put("/*", "authc");

        //modify login page
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //set unauthorized page
        shiroFilterFactoryBean.setUnauthorizedUrl("/toLogin");

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
    public ShiroRealm getShiroRealm(/*@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher*/){
        ShiroRealm shiroRealm = new ShiroRealm();
        /*shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);*/
        return shiroRealm;
    }

    /**
     * set ShiRoDialect for mix thymeleaf and shiro to use
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
