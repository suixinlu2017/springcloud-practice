package com.suixinlu.eurekaserver7071;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7071Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7071Application.class, args);
    }

}
