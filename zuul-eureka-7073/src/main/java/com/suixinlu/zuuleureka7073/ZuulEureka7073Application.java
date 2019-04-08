package com.suixinlu.zuuleureka7073;

import com.suixinlu.zuuleureka7073.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulEureka7073Application {
    @Bean
    public AccessFilter accessFilter(){return new AccessFilter();}

    public static void main(String[] args) {
        SpringApplication.run(ZuulEureka7073Application.class, args);
    }

}
