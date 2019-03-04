package com.springboot.config;

import com.springboot.commons.JWTToken;
import com.springboot.commons.JWTUtil;
import com.springboot.dto.UserDto;
import com.springboot.dto.User_UserRole;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //https://blog.csdn.net/q89757316/article/details/80693942
        String userID = JWTUtil.getUsername(principalCollection.toString());

//        Subject subject = SecurityUtils.getSubject();
//        UserDto userDto = (UserDto) subject.getPrincipal();
//        String userID = userDto.getId();
        UserDto user = userService.findById(userID);
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
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        UserDto userDto  = userService.findById(username);
        if (userDto == null) {
            throw new AuthenticationException("User didn't existed!");
        }

//        if (! JWTUtil.verify(token, username, userDto.getPassword())) {
//            throw new AuthenticationException("Username or password error");
//        }
        Object principal = userDto;

        Object credentials = userDto.getPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(userDto.getId());//Use account ID as salt value

        Object result = new SimpleHash("MD5", "123456", credentialsSalt, 1024);

//        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
