package com.example.demo.services;

public class FizzBuzzService {

    public String fizzBuzz(int number) {
        if (number % 35 == 0) {
            return "fizzbuzzbuzz";
        } else if (number % 3 == 0 && number % 5 == 0) {
            return "fizzbuzz";
        } else if (number % 3 == 0) {
            return "fizz";
        } else if (number % 5 == 0) {
            return "buzz";
        } else {
            return "";
        }
    }

    public int flipBits(String input) {
        int number = Integer.parseInt(input);
        String binaryString = Integer.toBinaryString(number);
        StringBuilder flippedBinaryString = new StringBuilder();

        for (char bit : binaryString.toCharArray()) {
            flippedBinaryString.append(bit == '0' ? '1' : '0');
        }

        return Integer.parseInt(flippedBinaryString.toString(), 2);
    }
}
