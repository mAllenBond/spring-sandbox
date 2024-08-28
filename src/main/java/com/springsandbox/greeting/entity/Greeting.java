package com.springsandbox.greeting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Greeting {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
        private UUID id;
        private String statement;
}

