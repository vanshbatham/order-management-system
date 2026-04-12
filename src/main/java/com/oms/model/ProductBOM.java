package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_bom")
@Schema(description = "Bill of Materials entry — defines components required to manufacture a product")
public class ProductBOM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated BOM entry ID", example = "1")
    private Long productBomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "The finished product being manufactured")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    @Schema(description = "The component or raw material required")
    private Product component;

    @Column(nullable = false)
    @Schema(description = "Quantity of this component needed", example = "4")
    private Integer quantity;
}