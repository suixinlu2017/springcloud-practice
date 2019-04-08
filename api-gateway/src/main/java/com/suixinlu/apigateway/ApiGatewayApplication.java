package com.suixinlu.apigateway;

import com.suixinlu.apigateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    //http://localhost:1118/api-b/add?a=1&b=24&accessToken=41231
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
