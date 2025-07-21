package com.example.JpaDemo;

import io.micrometer.common.util.internal.logging.InternalLogger;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;

@Builder
@Entity
@Table
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer age;
}