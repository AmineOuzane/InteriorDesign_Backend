package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Chaise;
import com.example.sketch20_backend.model.Chambre;
import com.example.sketch20_backend.repository.ChaiseRepository;
import com.example.sketch20_backend.repository.ChambreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChaiseService {

    private static final Logger logger = LoggerFactory.getLogger(ChaiseService.class);
    @Autowired
    ChaiseRepository chaiseRepository;

    public Optional<Chaise> getChaise(int id) {
        logger.info("Fetching chaise with ID: {}", id);
        Optional<Chaise> chaise = chaiseRepository.findById(id);
        if (chaise.isPresent()) {
            logger.debug("chaise found: {}", chaise.get());
        } else {
            logger.warn("chaise not found with ID: {}", id);
        }
        return chaise;
    }

    public List<Chaise> getAllChaises() {
        logger.info("Fetching all chaise");
        List<Chaise> chaises = chaiseRepository.findAll()
                .stream()
                .filter(chaise -> chaise != null)
                .collect(Collectors.toList());
        logger.info("Number of chambres found: {}", chaises.size());
        return chaises;
    }

}
