package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.model.Chambre;
import com.example.sketch20_backend.repository.ChambreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChambreService {
    private static final Logger logger = LoggerFactory.getLogger(ChambreService.class);
    @Autowired
    ChambreRepository chambreRepository;

    public Optional<Chambre> getChambre(int id) {
        logger.info("Fetching chambre with ID: {}", id);
        Optional<Chambre> chambre = chambreRepository.findById(id);
        if (chambre.isPresent()) {
            logger.debug("Chambre found: {}", chambre.get());
        } else {
            logger.warn("Chambre not found with ID: {}", id);
        }
        return chambre;
    }

    public List<Chambre> getAllChambres() {
        logger.info("Fetching all chambres");
        List<Chambre> chambres = chambreRepository.findAll()
                .stream()
                .filter(chambre -> chambre != null)
                .collect(Collectors.toList());
        logger.info("Number of chambres found: {}", chambres.size());
        return chambres;
    }

}
