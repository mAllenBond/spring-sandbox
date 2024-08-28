package com.springsandbox.greeting.service;

import com.springsandbox.greeting.entity.Greeting;
import com.springsandbox.greeting.entity.GreetingDTO;
import com.springsandbox.greeting.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GreetingService {

    private final GreetingRepository greetingRepository;


    public GreetingDTO createGreeting(String greeting) {
        Greeting newGreeting = new Greeting();
        newGreeting.setStatement(greeting);
        Greeting savedGreeting = greetingRepository.save(newGreeting);
        return GreetingDTO.createFromGreeting(savedGreeting);
    }

    // TODO: There's basically no error handling here, so this will throw an exception if the ID doesn't exist.

    public String getGreeting(UUID id) {
        Greeting greeting = greetingRepository.findById(id).orElseThrow();
        return greeting.getStatement();
    }

    // TODO: This is an interesting one. I'm returning a list of DTOs here, but I'm not sure if that's the best way to do it.
    // I also tried out a custom query, not a fan of magic string syntax though.

    public List<GreetingDTO> getGreetings() {
        return greetingRepository.findAll().stream()
                .map(GreetingDTO::createFromGreeting)
                .collect(Collectors.toList());
    }

    public GreetingDTO updateGreeting(GreetingDTO greeting) {
        Greeting updatedGreeting =  greetingRepository.findById(greeting.id()).orElseThrow();
        updatedGreeting.setId(greeting.id());
        updatedGreeting.setStatement(greeting.statement());
        Greeting savedGreeting = greetingRepository.save(updatedGreeting);
        return GreetingDTO.createFromGreeting(savedGreeting);
    }

    //TODO: Extremely scuffed delete method. A better approach would either use a try or an optional, and handle the possible exception.

    public Boolean deleteGreeting(UUID id) {
       greetingRepository.deleteById(id);
       return true;
    }
}
