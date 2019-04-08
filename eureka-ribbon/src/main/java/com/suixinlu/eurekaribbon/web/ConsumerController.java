package com.suixinlu.eurekaribbon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(@RequestParam Integer a,@RequestParam Integer b){
        return restTemplate.getForEntity("http://EUREKA-PREDUCT/add?a="+a+"&b="+b,String.class).getBody();//地址和返回值
        //http://localhost:1114/add?a=50&b=3
    }
}
