package com.example.demo.services;

import com.example.demo.dtos.DemoResponseDto;
import com.example.demo.services.DemoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoServiceTest {

    private final DemoService demoService = new DemoService();

    @Test
    public void testGetDemoResponse() {
        DemoResponseDto response = demoService.getDemoResponse();
        assertEquals("Hello from DemoService!", response.getMessage());
        assertEquals(2, response.getDetails().size());
    }

    @Test
    public void testGetConditionalDemoResponse_True() {
        DemoResponseDto response = demoService.getConditionalDemoResponse(true);
        assertEquals("Condition is true!", response.getMessage());
        assertEquals(2, response.getDetails().size());
    }

    @Test
    public void testGetConditionalDemoResponse_False() {
        DemoResponseDto response = demoService.getConditionalDemoResponse(false);
        assertEquals("Condition is false!", response.getMessage());
        assertEquals(2, response.getDetails().size());
    }

    @Test
    public void testAnotherServiceMethod() {
        DemoResponseDto response = demoService.anotherServiceMethod();
        assertEquals("Response from another service method!", response.getMessage());
        assertEquals(2, response.getDetails().size());
    }
}
