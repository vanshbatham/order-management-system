package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@Schema(description = "User entity stored in the database")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated user ID", example = "1")
    private Long userId;

    @Column(nullable = false)
    @Schema(description = "Full name", example = "Vansh Batham")
    private String name;

    @Column(nullable = false, unique = true)
    @Schema(description = "Unique email address", example = "vansh@example.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "BCrypt hashed password", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "User role", example = "SALES_REPRESENTATIVE")
    private Role role;
}