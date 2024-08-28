package com.springsandbox.greeting.controller;

import com.springsandbox.greeting.entity.GreetingDTO;
import com.springsandbox.greeting.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    private UUID sampleId;
    private GreetingDTO sampleGreetingDTO;

    @BeforeEach
    void setUp() {
        sampleId = UUID.randomUUID();
        sampleGreetingDTO = new GreetingDTO(sampleId, "Hello, World!");
    }

    @Test
    void greeting() throws Exception {
        mockMvc.perform(get("/api/greeting/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void getGreeting() throws Exception {
        when(greetingService.getGreeting(sampleId)).thenReturn("Hello, World!");

        mockMvc.perform(get("/api/greeting/{id}", sampleId))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    void getGreetings() throws Exception {
        when(greetingService.getGreetings()).thenReturn(Collections.singletonList(sampleGreetingDTO));

        mockMvc.perform(get("/api/greeting/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sampleId.toString()))
                .andExpect(jsonPath("$[0].statement").value("Hello, World!"));
    }

    @Test
    void createGreeting() throws Exception {
        when(greetingService.createGreeting(anyString())).thenReturn(sampleGreetingDTO);

        mockMvc.perform(post("/api/greeting/")
                        .contentType("application/json")
                        .content("\"Hello, World!\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleId.toString()))
                .andExpect(jsonPath("$.statement").value("Hello, World!"));
    }

    @Test
    void updateGreeting() throws Exception {
        when(greetingService.updateGreeting(any(GreetingDTO.class))).thenReturn(sampleGreetingDTO);

        mockMvc.perform(put("/api/greeting/")
                        .contentType("application/json")
                        .content("{\"id\":\"" + sampleId + "\", \"value\":\"Hello, World!\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleId.toString()))
                .andExpect(jsonPath("$.statement").value("Hello, World!"));
    }

    @Test
    void deleteGreeting() throws Exception {
        when(greetingService.deleteGreeting(sampleId)).thenReturn(true);

        mockMvc.perform(delete("/api/greeting/{id}", sampleId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
