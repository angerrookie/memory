package com.pubinfo.memory;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.pubinfo.memory.repository")
public class MemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryApplication.class, args);
    }

    /**
     * 功能描述: TomcatServletWebServerFactory() ⽅法主要是为了解决上传文件较大时出现连接重置的问题，这个异常后台是捕捉不到的：
     * @Param: []
     * @Return: org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     * @Author: Administrator
     * @Date: 2020/4/29 12:43
     */
    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<
                                ?>)) {
                //-1 means unlimited
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
        });
        return tomcat;
    }

}
