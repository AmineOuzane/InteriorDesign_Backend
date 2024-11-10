package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.ProductDTO;
import com.example.sketch20_backend.model.Product;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.service.ProductService;
import com.example.sketch20_backend.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    // Existing method to get a single product by ID
    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        logger.info("Received request to get product with ID: {}", id);

        Optional<Product> product = productService.getProduct(id);

        if (product.isPresent()) {
            Product p = product.get();
            logger.debug("Product found: {}", p);

            String base64Image = Base64.getEncoder().encodeToString(p.getImage());
            ProductDTO productDTO = ObjectMapperUtils.convertToDto(p);
            productDTO.setImage(base64Image);

            logger.info("Returning product DTO: {}", productDTO);
            return productDTO;
        } else {
            logger.error("Product with ID {} not found", id);
            throw new ProductNotFoundException("Product not found");
        }
    }

    // New method to get all products
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        logger.info("Received request to get all products");

        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> {
                    String base64Image = Base64.getEncoder().encodeToString(product.getImage());
                    ProductDTO productDTO = ObjectMapperUtils.convertToDto(product);
                    productDTO.setImage(base64Image);
                    return productDTO;
                })
                .collect(Collectors.toList());

        logger.info("Returning all products");
        return productDTOs;
    }
}
