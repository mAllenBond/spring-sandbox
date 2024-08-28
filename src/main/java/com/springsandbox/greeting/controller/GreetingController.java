package com.springsandbox.greeting.controller;

import com.springsandbox.greeting.entity.GreetingDTO;
import com.springsandbox.greeting.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(GreetingController.API_BASE_PATH)
@RequiredArgsConstructor
public class GreetingController {
    public static final String API_BASE_PATH = "/api/greeting";

    private final GreetingService greetingService;

    @GetMapping("/")
    public String greeting() {
        return "Hello, World!";
    }

    @GetMapping("/{id}")
    public String getGreeting(@PathVariable UUID id) {
        return greetingService.getGreeting(id);
    }

    @GetMapping("/all")
    public List<GreetingDTO> getGreetings() {
        return greetingService.getGreetings();
    }

    @PostMapping("/")
    public GreetingDTO createGreeting(@RequestBody String greeting) {
        return greetingService.createGreeting(greeting);
    }

    @PutMapping("/")
    public GreetingDTO updateGreeting(@RequestBody GreetingDTO greeting) {
        return greetingService.updateGreeting(greeting);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteGreeting(@PathVariable UUID id) {
        return greetingService.deleteGreeting(id);
    }
}
