package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
@Schema(description = "Product entity — both finished goods and raw materials")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated product ID", example = "1")
    private Long productId;

    @Column(nullable = false)
    @Schema(description = "Product name", example = "UPS System")
    private String productName;

    @Schema(description = "Product description", example = "Industrial UPS for power backup")
    private String description;

    @Column(nullable = false)
    @Schema(description = "Unit price in rupees", example = "15000.00")
    private Double unitPrice;

    @Column(nullable = false)
    @Schema(description = "Current stock quantity", example = "50")
    private Integer quantityInStock;
}