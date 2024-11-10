package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.repository.CanapeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CanapeService {
    private static final Logger logger = LoggerFactory.getLogger(CanapeService.class);

    @Autowired
    private CanapeRepository canapeRepository;

    // Method to get a single canape by ID
    public Optional<Canape> getCanape(int id) {
        logger.info("Fetching canape with ID: {}", id);
        Optional<Canape> canape = canapeRepository.findById(id);
        if (canape.isPresent()) {
            logger.debug("Canape found: {}", canape.get());
        } else {
            logger.warn("Canape not found with ID: {}", id);
        }
        return canape;
    }

    // Method to get all canapes
    public List<Canape> getAllCanapes() {
        logger.info("Fetching all canapes");
        List<Canape> canapes = canapeRepository.findAll()
                .stream()
                .filter(canape -> canape != null)
                .collect(Collectors.toList());
        logger.info("Number of canapes found: {}", canapes.size());
        return canapes;
    }
}
