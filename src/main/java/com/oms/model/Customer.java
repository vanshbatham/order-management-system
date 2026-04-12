package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
@Schema(description = "Customer entity — represents a buyer placing sales orders")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated customer ID", example = "1")
    private Long customerId;

    @Column(nullable = false)
    @Schema(description = "Customer company or person name", example = "ABC Corp")
    private String name;

    @Column(nullable = false, unique = true)
    @Schema(description = "Unique customer email", example = "abc@corp.com")
    private String email;

    @Schema(description = "Customer phone number", example = "9999999999")
    private String phone;

    @Schema(description = "Customer address", example = "Indore, Madhya Pradesh")
    private String address;
}