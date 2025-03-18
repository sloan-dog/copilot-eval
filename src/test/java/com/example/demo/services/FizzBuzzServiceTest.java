package com.example.demo.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzServiceTest {

    private final FizzBuzzService fizzBuzzService = new FizzBuzzService();

    @Test
    public void testFizzBuzz() {
        assertEquals("fizzbuzzbuzz", fizzBuzzService.fizzBuzz(35));
        assertEquals("fizzbuzz", fizzBuzzService.fizzBuzz(15));
        assertEquals("fizz", fizzBuzzService.fizzBuzz(3));
        assertEquals("buzz", fizzBuzzService.fizzBuzz(5));
        assertEquals("", fizzBuzzService.fizzBuzz(1));
    }

    @Test
    public void testFlipBits() {
        assertEquals(10, fizzBuzzService.flipBits("5"));
        assertEquals(5, fizzBuzzService.flipBits("10"));
    }
}
