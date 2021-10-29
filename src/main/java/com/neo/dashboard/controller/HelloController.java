package com.neo.dashboard.controller;

import com.neo.dashboard.models.ResultDTO;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResultDTO show(){
        return new ResultDTO("thangtv", 23, true);
    }

    @PostMapping("/add")
    public String add(@RequestBody ResultDTO resultDTO){
        if(resultDTO.getName()== null){
            return "Insert failed !!!";
        }
        return "Insert successful: " + resultDTO.toString();
    }

    @GetMapping("/test/{check}")
    public String test(@PathVariable("check") String check){
        return "Insert successful: " + check;
    }
}





