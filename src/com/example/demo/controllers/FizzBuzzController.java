package com.example.demo.controllers;

import com.example.demo.dtos.DemoResponseDto;
import com.example.demo.services.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class FizzBuzzController {

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @GetMapping("/fizzbuzz")
    public DemoResponseDto getFizzBuzz(@RequestParam int number) {
        String result = fizzBuzzService.fizzBuzz(number); // Reverted to call the original method
        DemoResponseDto response = new DemoResponseDto();
        response.setMessage("FizzBuzz Result");
        response.setDetails(List.of(result));
        return response;
    }

    @GetMapping("/flip-bits")
    public ResponseEntity<DemoResponseDto> getFlipBits(@RequestParam int number) {
        if (number < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        String result = fizzBuzzService.flipBits(number); // Updated to call the correct method
        DemoResponseDto response = new DemoResponseDto();
        response.setMessage("Flip Bits Result");
        response.setDetails(List.of(result));
        return ResponseEntity.ok(response);
    }
}
