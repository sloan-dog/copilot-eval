package com.example.demo.controllers;

import com.example.demo.dtos.DemoResponseDto;
import com.example.demo.services.DemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DemoControllerTest {

    @Mock
    private DemoService demoService;

    @InjectMocks
    private DemoController demoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDemo() {
        DemoResponseDto expectedResponse = new DemoResponseDto();
        expectedResponse.setMessage("Hello from DemoService!");
        when(demoService.getDemoResponse()).thenReturn(expectedResponse);

        DemoResponseDto response = demoController.getDemo();

        assertEquals("Hello from DemoService!", response.getMessage());
    }

    @Test
    public void testGetConditionalDemo_True() {
        DemoResponseDto expectedResponse = new DemoResponseDto();
        expectedResponse.setMessage("Condition is true!");
        when(demoService.getConditionalDemoResponse(true)).thenReturn(expectedResponse);

        DemoResponseDto response = demoController.getConditionalDemo(true);

        assertEquals("Condition is true!", response.getMessage());
    }

    @Test
    public void testGetConditionalDemo_False() {
        DemoResponseDto expectedResponse = new DemoResponseDto();
        expectedResponse.setMessage("Condition is false!");
        when(demoService.getConditionalDemoResponse(false)).thenReturn(expectedResponse);

        DemoResponseDto response = demoController.getConditionalDemo(false);

        assertEquals("Condition is false!", response.getMessage());
    }
}
