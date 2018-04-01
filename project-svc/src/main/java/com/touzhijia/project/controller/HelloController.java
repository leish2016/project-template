package com.touzhijia.project.controller;


import com.touzhijia.project.domain.dto.RandomValue;
import com.touzhijia.project.remote.RestClientService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by leish on 2017/11/4.
 */
@RestController
@RequestMapping("api/")
public class HelloController {
    @Autowired
    RestClientService restClientService;

    @GetMapping("hello/{name}")
    public String sayHi(@PathVariable String name){
        return "Hi "+name;
    }

    @GetMapping("/random")
    @ApiOperation("RestTemplate演示")
    public RandomValue greeting() {
        return restClientService.getValue();
    }
}
