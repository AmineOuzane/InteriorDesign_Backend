package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Chaise;
import com.example.sketch20_backend.model.Habillage;
import com.example.sketch20_backend.repository.ChaiseRepository;
import com.example.sketch20_backend.repository.HabillageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabillageService {

    private static final Logger logger = LoggerFactory.getLogger(HabillageService.class);
    @Autowired
    HabillageRepository habillageRepository;

    public Optional<Habillage> getHabillage(int id) {
        logger.info("Fetching habillage with ID: {}", id);
        Optional<Habillage> habillage = habillageRepository.findById(id);
        if (habillage.isPresent()) {
            logger.debug("habillage found: {}", habillage.get());
        } else {
            logger.warn("habillage not found with ID: {}", id);
        }
        return habillage;
    }

    public List<Habillage> getAllHabilages() {
        logger.info("Fetching all habillage");
        List<Habillage> habillages = habillageRepository.findAll()
                .stream()
                .filter(habillage -> habillage != null)
                .collect(Collectors.toList());
        logger.info("Number of habillage found: {}", habillages.size());
        return habillages;
    }
}
