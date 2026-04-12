package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
@Schema(description = "Supplier entity — represents a vendor supplying components")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated supplier ID", example = "1")
    private Long supplierId;

    @Column(nullable = false)
    @Schema(description = "Supplier company name", example = "PowerTech Supplies")
    private String name;

    @Column(nullable = false, unique = true)
    @Schema(description = "Unique supplier email", example = "supply@powertech.com")
    private String email;

    @Schema(description = "Supplier phone number", example = "8888888888")
    private String phone;

    @Schema(description = "Supplier address", example = "Mumbai, Maharashtra")
    private String address;
}