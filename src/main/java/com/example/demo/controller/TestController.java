package com.example.demo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class TestController {

    @Resource
    public RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/hi")
    public String hello() {
        redisTemplate.opsForValue().set("name", "alex");
        System.out.println("------------------------");
        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println("------------------------");
        return "succss";
    }


    @GetMapping("/hello")
    public String sayHello(){
        return "hello security";
    }
}
