package com.example.demo.controller;


import com.example.demo.service.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/rpc")
    public String rpc(){
        return CommonService.demoService.sayFuck("马勒戈壁");
    }
}
