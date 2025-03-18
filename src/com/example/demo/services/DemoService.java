package com.example.demo.services;

import com.example.demo.dtos.DemoResponseDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DemoService {

    public DemoResponseDto getDemoResponse() {
        DemoResponseDto response = new DemoResponseDto();
        response.setMessage("Hello from DemoService!");
        response.setDetails(Arrays.asList("Detail 1", "Detail 2"));
        return response;
    }

    public DemoResponseDto getConditionalDemoResponse(boolean condition) {
        DemoResponseDto response = new DemoResponseDto();
        if (condition) {
            response.setMessage("Condition is true!");
            response.setDetails(Arrays.asList("True Detail 1", "True Detail 2"));
        } else {
            response.setMessage("Condition is false!");
            response.setDetails(Arrays.asList("False Detail 1", "False Detail 2"));
        }
        return response;
    }

    public DemoResponseDto anotherServiceMethod() {
        DemoResponseDto response = new DemoResponseDto();
        response.setMessage("Response from another service method!");
        response.setDetails(Arrays.asList("Another Detail 1", "Another Detail 2"));
        return response;
    }
}
