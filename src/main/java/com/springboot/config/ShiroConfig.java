package com.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.springboot.commons.JWTFilter;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     *      Shiro inside filter :execute the filter about roles
     *          useful filter:
     *              anon: 无需认证（登录）可以访问
     *              authc:需要认证才可以访问
     *              user:如果使用remember可以直接访问
     *              perms：该资源必须得到权限才可以访问
     *              roles：该资源必须得到角色权限才可以访问
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> jwtFilterMap = new HashMap<>();
        jwtFilterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(jwtFilterMap);

        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        ///-----------------jwt
        filterMap.put("/user/*", "jwt");
        filterMap.put("/userRole/*", "jwt");

        //-----------------anon
        filterMap.put("/", "anon");
        filterMap.put("/toLogin", "anon");
        filterMap.put("/login.html", "anon");

        //-------------------role
        filterMap.put("/user.html", "roles[ROL0001]");
        filterMap.put("/user-role.html", "roles[ROL0001]");
        filterMap.put("/appointment.html", "roles[ROL0003]");
        filterMap.put("/diagnosis.html", "roles[ROL0002]");
        filterMap.put("/registration.html", "roles[ROL0003]");
        filterMap.put("/drug.html", "roles[ROL0003]");
        filterMap.put("/dispensing.html", "roles[ROL0003]");

        //------------------logout
        filterMap.put("/logout.action", "logout");

        //------------------authc
        filterMap.put("/index.html", "authc");
        filterMap.put("/*", "authc");

        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/401.html");
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

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
