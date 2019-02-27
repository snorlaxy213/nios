package com.springboot.config;

import com.springboot.dto.UserDto;
import com.springboot.dto.User_UserRole;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        UserDto userDto = (UserDto) subject.getPrincipal();
        String userID = userDto.getId();
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
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        UserDto userDto  = userService.findById(username);

        if(userDto == null){
            throw new UnknownAccountException("user is not exist");
        }

        Object principal = userDto;
        //Unencrypted password
        Object credentials = userDto.getPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(userDto.getId());//Use account ID as salt value

        Object result = new SimpleHash("MD5", "256254", credentialsSalt, 1024);
        Boolean flag = credentials.toString().equals(result.toString());
        //Determine if the password is correct
        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);

        //judge if password is right
        //principal : AuthenticationInfo entity information
        //credentials : Password
        //realmName : current realm's name;you can return getName();
//        return new SimpleAuthenticationInfo(userDto,userDto.getPassword(),"user");
    }
}
