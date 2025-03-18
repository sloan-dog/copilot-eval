package com.example.demo.dtos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoResponseDtoTest {

    @Test
    public void testGettersAndSetters() {
        DemoResponseDto dto = new DemoResponseDto();
        String message = "Test Message";
        List<String> details = Arrays.asList("Detail 1", "Detail 2");

        dto.setMessage(message);
        dto.setDetails(details);

        assertEquals(message, dto.getMessage());
        assertEquals(details, dto.getDetails());
    }
}
