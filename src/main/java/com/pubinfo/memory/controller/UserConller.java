package com.pubinfo.memory.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pubinfo.memory.common.dto.ResponseReturn;
import com.pubinfo.memory.entity.User;
import com.pubinfo.memory.service.IUserService;
import com.pubinfo.memory.utils.MD5;
import com.pubinfo.memory.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sys_user")
public class UserConller {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseReturn login(User user, HttpServletRequest request){
        System.out.println("---login--"+user);
        String url = "login";
        //使用shiro编写认证操作
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken passwordToken = new UsernamePasswordToken(user.getUserName(), MD5.encoding(user.getPassWord()));
        //执行登录方法
        try {
            subject.login(passwordToken);
            User user1 = userService.getOne(new QueryWrapper<User>().eq("user_name",user.getUserName()));
            request.getSession().setAttribute("loginUser", user);
            return ResponseReturn.success("成功");
        } catch (UnknownAccountException e) {
            //登录失败：用户名不存在
            request.getSession().setAttribute("lo_sno_error","用户名不存在");
            request.getSession().removeAttribute("loginUser");
            url = "redirect:/login.html";
            return ResponseReturn.success("用户名不存在");
        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            request.getSession().setAttribute("lo_pwd_error","密码错误");
            request.getSession().removeAttribute("loginUser");
            url = "redirect:/login.html";
            return ResponseReturn.success("密码错误");
        }
    }

}
