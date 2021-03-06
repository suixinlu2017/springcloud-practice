package com.suixinlu.microserviceconsumermoviefeign.web;

import com.suixinlu.microserviceconsumermoviefeign.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/user/{id}")
    public String findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }
}
