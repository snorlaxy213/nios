package com.springboot.config;

import com.springboot.commons.JWTToken;
import com.springboot.commons.JWTUtil;
import com.springboot.dto.UserDto;
import com.springboot.dto.User_UserRole;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(ShiroRealm.class);


    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userID = JWTUtil.getUsername(principalCollection.toString());

//        UserDto user = userService.findById(userID);
        UserDto user = userMapper.findUserByID(userID);
        Collection<String> rolesCollection = new HashSet<>();

        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<User_UserRole> user_userRole = userRoleService.findUser_Role(user.getId());
            for (User_UserRole userRole : user_userRole) {
                rolesCollection.add(userRole.getUserRoleId());
            }

            info.addRoles(rolesCollection);
            return info;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserDto userDto  = userService.findById(username);
        if (userDto == null) {
            throw new UnknownAccountException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userDto.getPassword())) {
            throw new IncorrectCredentialsException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
