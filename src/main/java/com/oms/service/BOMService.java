package com.oms.service;

import com.oms.dto.request.BOMRequest;
import com.oms.dto.response.BOMResponse;
import com.oms.exception.BadRequestException;
import com.oms.exception.DuplicateResourceException;
import com.oms.exception.ResourceNotFoundException;
import com.oms.model.Product;
import com.oms.model.ProductBOM;
import com.oms.repository.ProductBOMRepository;
import com.oms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BOMService {

    private final ProductBOMRepository productBOMRepository;

    private final ProductRepository productRepository;

    public BOMResponse addComponent(BOMRequest request) {

        // validate product exists
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.getProductId()));

        // validate component exists
        Product component = productRepository.findById(request.getComponentId())
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + request.getComponentId()));

        //a product cannot be its own component
        if (request.getProductId().equals(request.getComponentId())) {
            throw new BadRequestException("A product cannot be a component of itself");
        }

        //prevent duplicate component in same BOM
        if (productBOMRepository.existsByProduct_ProductIdAndComponent_ProductId(
                request.getProductId(), request.getComponentId())) {
            throw new DuplicateResourceException("This component already exists in the BOM for this product");
        }

        ProductBOM bom = new ProductBOM();
        bom.setProduct(product);
        bom.setComponent(component);
        bom.setQuantity(request.getQuantity());

        return mapToResponse(productBOMRepository.save(bom));
    }

    public List<BOMResponse> getBOMByProductId(Long productId) {

        // validate product exists
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }

        return productBOMRepository.findByProduct_ProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void removeComponent(Long bomId) {
        if (!productBOMRepository.existsById(bomId)) {
            throw new ResourceNotFoundException("BOM entry not found with id: " + bomId);
        }
        productBOMRepository.deleteById(bomId);
    }

    // helper method to map ProductBOM to BOMResponse
    private BOMResponse mapToResponse(ProductBOM bom) {
        return new BOMResponse(
                bom.getProductBomId(),
                bom.getProduct().getProductId(),
                bom.getProduct().getProductName(),
                bom.getComponent().getProductId(),
                bom.getComponent().getProductName(),
                bom.getQuantity()
        );
    }
}