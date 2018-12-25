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
        User user = (User) subject.getPrincipal();
        info.addStringPermission("admin");

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //write shiro judgment logic ,judge username and password
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User user  = userService.findById(username);

        if(user == null){
            //if user is not exist ,shiro will return UnknownAccountException
            throw new UnknownAccountException("user is not exist");
        }

        //judge if password is right
        //principal : AuthenticationInfo entity information
        //credentials : Password
        //realmName : currnt realm's name;you can return getName();
        return new SimpleAuthenticationInfo(user,user.getPassword(),"user");
    }
}
