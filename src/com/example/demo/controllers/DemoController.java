package com.example.demo.controllers;

import com.example.demo.services.DemoService;
import com.example.demo.dtos.DemoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping
    public DemoResponseDto getDemo() {
        return demoService.getDemoResponse();
    }

    @GetMapping("/conditional")
    public DemoResponseDto getConditionalDemo(boolean condition) {
        return demoService.getConditionalDemoResponse(condition);
    }
}
