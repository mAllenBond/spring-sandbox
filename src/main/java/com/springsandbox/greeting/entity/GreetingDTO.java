package com.springsandbox.greeting.entity;

import java.util.UUID;

/**
 * There are multiple ways to define and build DTOs, I'm using a record here for no real reason except that I don't
 * expect to mutate this or add any behavior to it. If I did, I would use a class. There is a good discussion to be had
 * on whether DTOs are always necessary. In this case, I'm using it to avoid exposing the entity directly to the
 * controller.
 */

public record GreetingDTO(
        UUID id,
        String statement
) {

    /**
     * This is a good discussion point around builders vs static factory methods vs mappers!
     */

    public static GreetingDTO createFromGreeting(Greeting greeting) {
        return new GreetingDTO(greeting.getId(), greeting.getStatement());
    }
}
