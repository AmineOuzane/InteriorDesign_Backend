package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Product;
import com.example.sketch20_backend.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    // Existing method to get a single product by ID
    public Optional<Product> getProduct(int id) {
        logger.info("Fetching product with ID: {}", id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            logger.debug("Product found: {}", product.get());
        } else {
            logger.warn("Product not found with ID: {}", id);
        }
        return product;
    }

    // New method to get all products
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        logger.info("Number of products found: {}", products.size());
        return products;
    }
}
