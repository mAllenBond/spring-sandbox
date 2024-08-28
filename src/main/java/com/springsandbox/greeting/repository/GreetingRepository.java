package com.springsandbox.greeting.repository;

import com.springsandbox.greeting.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, UUID> {
}
