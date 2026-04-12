package com.oms.service;

import com.oms.exception.ResourceNotFoundException;
import com.oms.model.Product;
import com.oms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {

        log.info("Creating product. name={}, price={}, stock={}",
                product.getProductName(),
                product.getUnitPrice(),
                product.getQuantityInStock());

        Product saved = productRepository.save(product);

        log.info("Product created successfully. productId={}, name={}",
                saved.getProductId(),
                saved.getProductName());

        return saved;
    }

    public List<Product> getAllProducts() {

        log.info("Fetching all products");

        List<Product> products = productRepository.findAll();

        log.info("Fetched {} products", products.size());

        return products;
    }

    public Product getProductById(Long id) {

        log.info("Fetching product by id={}", id);

        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found. id={}", id);
                    return new ResourceNotFoundException("Product not found with id: " + id);
                });
    }
}