package com.springboot.config;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //give resource authorization
        //info.addStringPermission("user:add");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPreviousPrincipals();
        info.addStringPermission("admin");

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //write shiro judgment logic ,judge username and password
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User user = null;
        try {
            user = userService.findByname(username);
        } catch (Exception e) {

        }


        if(user == null){
            //if user is not exist
            return null;   //shiro will return UnknownAccountException
        }

        //judge if password is right
        //tips: method second element must will be database password
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
