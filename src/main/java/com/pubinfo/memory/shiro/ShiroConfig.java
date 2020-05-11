package com.pubinfo.memory.shiro;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述:Shiro配置类
 * @Param:
 * @Return:
 * @Author: Administrator
 * @Date: 2020/5/9 17:01
 */
@Configuration
public class ShiroConfig {


    /**
     * 功能描述: 创建ShiroFilterFactoryBean
     * @Param: [securityManager]
     * @Return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Author: Administrator
     * @Date: 2020/5/9 17:42
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器  可以实现权限相关的拦截
        //常用过滤器
       /* anon:无需认证（登录）可以访问
        authc:必须认证才可以访问
        user：如果使用rememberMe的功能可以直接访问
        perms:该资源必须得到资源权限才可以访问
        role:该资源必须得到角色授权才可以访问
        */
        Map<String,String> perms = new LinkedHashMap<>();
        perms.put("/","anon");
        perms.put("/sys_user/login","anon");
        perms.put("/files/exit","anon");
        perms.put("/files/*","authc");
        //修改登录跳转页面
        filterFactoryBean.setLoginUrl("/");
        filterFactoryBean.setFilterChainDefinitionMap(perms);

        return filterFactoryBean;
    }

    /**
     * 功能描述: 创建DefaultWebSecurityManager
     * @Param: [userRealm]
     * @Return: org.apache.shiro.web.mgt.DefaultWebSecurityManager
     * @Author: Administrator
     * @Date: 2020/5/9 16:57
     */
    @Bean(name ="securityManager")
    public DefaultWebSecurityManager getDDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    /**
     * 功能描述:创建Realm
     * @Param:
     * @Return:
     * @Author: Administrator
     * @Date: 2020/5/9 16:52
     */
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
