package com.kytc;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 何志同
 * @Date: 2020/9/10 11:54
 * @Description:
 **/
@EnableAutoConfiguration
@Slf4j
@EnableSwagger2
@EnableSwaggerBootstrapUI
@SpringCloudApplication
@EnableDiscoveryClient
@EnableCaching
@MapperScan("com.kytc.user.dao.mapper")
@EnableAspectJAutoProxy
@EnableScheduling
@EnableApolloConfig
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
        //log.info("Bootstrap started!");
    }
}