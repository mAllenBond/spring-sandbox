package com.springsandbox.greeting.service;

import com.springsandbox.greeting.entity.Greeting;
import com.springsandbox.greeting.entity.GreetingDTO;
import com.springsandbox.greeting.repository.GreetingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GreetingServiceTest {

    @Autowired
    private GreetingService greetingService;

    @Autowired
    private GreetingRepository greetingRepository;

    private UUID sampleId;
    private Greeting sampleGreeting;

    @BeforeEach
    void setUp() {
        sampleGreeting = new Greeting();
        sampleGreeting.setStatement("Hello, World!");
        sampleGreeting = greetingRepository.save(sampleGreeting);
        sampleId = sampleGreeting.getId();
    }

    @Test
    void createGreeting() {
        GreetingDTO newGreeting = greetingService.createGreeting("Hi there!");
        assertNotNull(newGreeting.id());
        assertEquals("Hi there!", newGreeting.statement());
    }

    @Test
    void getGreeting() {
        String greeting = greetingService.getGreeting(sampleId);
        assertEquals("Hello, World!", greeting);
    }

    @Test
    void getGreetings() {
        List<GreetingDTO> greetings = greetingService.getGreetings();
        assertEquals(1, greetings.size());
        assertEquals(sampleId, greetings.get(0).id());
        assertEquals("Hello, World!", greetings.get(0).statement());
    }

    @Test
    void updateGreeting() {
        GreetingDTO updatedGreeting = new GreetingDTO(sampleId, "Updated Greeting");
        GreetingDTO result = greetingService.updateGreeting(updatedGreeting);
        assertEquals(sampleId, result.id());
        assertEquals("Updated Greeting", result.statement());
    }

    @Test
    void deleteGreeting() {
        boolean result = greetingService.deleteGreeting(sampleId);
        assertTrue(result);
        assertFalse(greetingRepository.findById(sampleId).isPresent());
    }
}
