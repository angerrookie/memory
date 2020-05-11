package com.pubinfo.memory.confg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator
 * @description
 * @date 2020/1/15
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/Login.html").setViewName("login");
                registry.addViewController("/list.html").setViewName("list");

            }
            //注册拦截器
            //静态资源*.css  *.js springboot已经做好了静态映射
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                super.addInterceptors(registry);
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")//拦截访问路径
//                        .excludePathPatterns("/","/login","/index.html","/index","/webjars/**","/asserts/**");//排除登录路径
            }

        };
        return adapter;
    }
   /* @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }*/

}
