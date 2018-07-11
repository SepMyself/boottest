package com.example.boottest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/goodbye")
    @ResponseBody
    public String goodbye(){
        return "goodbye";
    }

    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    public @ResponseBody String hello(@PathVariable int id, String name){
        return id + name + "hello";
    }
}
