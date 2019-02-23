package com.springboot.config;

import com.springboot.dto.UserDto;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    /**
     * 为用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        UserDto userDto = (UserDto) subject.getPrincipal();
        String userID = userDto.getId();
        UserDto user = userService.findById(userID);

        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addStringPermission("admin");

            return info;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //write shiro judgment logic ,judge username and password
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        UserDto userDto  = userService.findById(username);

        if(userDto == null){
            //if user is not exist ,shiro will return UnknownAccountException
            throw new UnknownAccountException("user is not exist");
        }

        //judge if password is right
        //principal : AuthenticationInfo entity information
        //credentials : Password
        //realmName : current realm's name;you can return getName();
        return new SimpleAuthenticationInfo(userDto,userDto.getPassword(),"user");
    }
}
