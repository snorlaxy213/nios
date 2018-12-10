package com.springboot.config;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("execute Authorization logic ");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("execute Authentication logic ");
        //write shiro judgment logic ,judge username and password

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User user = userService.findByname(username);

        if(user == null){
            //if user is not exist
            return null;   //shiro will return UnknownAccountException
        }

        //judge if password is right
        //tips: method second element must will be database password
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
