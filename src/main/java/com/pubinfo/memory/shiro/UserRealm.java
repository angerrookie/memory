package com.pubinfo.memory.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pubinfo.memory.entity.User;
import com.pubinfo.memory.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserRealm extends AuthorizingRealm {
    
    /**
     * 功能描述: 执行授权逻辑
     * @Param: [principalCollection]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: Administrator
     * @Date: 2020/5/9 16:51
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("-----执行授权逻辑");

        return null;
    }
    @Autowired
    @Qualifier("userService")
    private IUserService userService;
    /**
     * 功能描述: 执行认证逻辑
     * @Param: [authenticationToken]
     * @Return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: Administrator
     * @Date: 2020/5/9 16:51
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name",token.getUsername()));
        if (user==null){
            //用户名不存在
            return null;//shiro底层抛出UnknownAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo("",user.getPassWord(),"");
    }
}
