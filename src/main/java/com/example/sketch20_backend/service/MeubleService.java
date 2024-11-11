package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.model.Meuble;
import com.example.sketch20_backend.repository.CanapeRepository;
import com.example.sketch20_backend.repository.MeubleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeubleService {
    private static final Logger logger = LoggerFactory.getLogger(MeubleService.class);

    @Autowired
    private MeubleRepository meubleRepository;

    public Optional<Meuble> getMeuble(int id) {
        logger.info("Fetching meuble with ID: {}", id);
        Optional<Meuble> meuble = meubleRepository.findById(id);
        if (meuble.isPresent()) {
            logger.debug("meuble found: {}", meuble.get());
        } else {
            logger.warn("meuble not found with ID: {}", id);
        }
        return meuble;
    }

    public List<Meuble> getAllMeubles() {
        logger.info("Fetching all meubles");
        List<Meuble> meubles = meubleRepository.findAll()
                .stream()
                .filter(meuble -> meuble != null)
                .collect(Collectors.toList());
        logger.info("Number of meuble found: {}", meubles.size());
        return meubles;
    }
}
