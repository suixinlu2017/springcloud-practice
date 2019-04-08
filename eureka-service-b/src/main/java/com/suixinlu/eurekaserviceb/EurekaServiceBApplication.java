package com.suixinlu.eurekaserviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceBApplication.class, args);
    }

}
