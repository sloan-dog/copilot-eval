package com.example.demo.controllers;

import com.example.demo.dtos.DemoResponseDto;
import com.example.demo.services.FizzBuzzService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FizzBuzzControllerTest {

    @Mock
    private FizzBuzzService fizzBuzzService;

    @InjectMocks
    private FizzBuzzController fizzBuzzController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFizzBuzz() {
        int number = 15;
        String expectedResult = "fizzbuzz";
        when(fizzBuzzService.fizzBuzz(number)).thenReturn(expectedResult);

        DemoResponseDto response = fizzBuzzController.getFizzBuzz(number);

        assertEquals("FizzBuzz Result", response.getMessage());
        assertEquals(1, response.getDetails().size());
        assertEquals(expectedResult, response.getDetails().get(0));
    }

    @Test
    public void testGetFlipBits_ValidNumber() {
        int number = 5;
        int expectedResult = 10;
        when(fizzBuzzService.flipBits("5")).thenReturn(expectedResult);

        ResponseEntity<DemoResponseDto> response = fizzBuzzController.getFlipBits(number);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Flip Bits Result", response.getBody().getMessage());
        assertEquals(1, response.getBody().getDetails().size());
        assertEquals(String.valueOf(expectedResult), response.getBody().getDetails().get(0));
    }

    @Test
    public void testGetFlipBits_InvalidNumber() {
        int number = 0;

        ResponseEntity<DemoResponseDto> response = fizzBuzzController.getFlipBits(number);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
