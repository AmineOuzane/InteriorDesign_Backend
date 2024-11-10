package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.CanapeDTO;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.service.CanapeService;
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
@RequestMapping("/canapes")
public class CanapeController {
    private static final Logger logger = LoggerFactory.getLogger(CanapeController.class);

    @Autowired
    private CanapeService canapeService;

    // Existing method to get a single canape by ID
    @GetMapping("/{id}")
    public CanapeDTO getCanape(@PathVariable int id) {
        logger.info("Received request to get canape with ID: {}", id);

        Optional<Canape> canape = canapeService.getCanape(id);

        if (canape.isPresent()) {
            Canape p = canape.get();
            logger.debug("Canape found: {}", p);

            String base64Image = Base64.getEncoder().encodeToString(p.getImage());
            CanapeDTO canapeDTO = ObjectMapperUtils.convertToDto(p);
            canapeDTO.setImage(base64Image);

            logger.info("Returning canape DTO: {}", canapeDTO);
            return canapeDTO;
        } else {
            logger.error("Canape with ID {} not found", id);
            throw new ProductNotFoundException("Canape not found");
        }
    }

    // New method to get all canapes
    @GetMapping
    public List<CanapeDTO> getAllCanapes() {
        logger.info("Received request to get all canapes");

        List<Canape> canapes = canapeService.getAllCanapes();
        List<CanapeDTO> canapeDTOs = canapes.stream()
                .map(canape -> {
                    String base64Image = Base64.getEncoder().encodeToString(canape.getImage());
                    CanapeDTO canapeDTO = ObjectMapperUtils.convertToDto(canape);
                    canapeDTO.setImage(base64Image);
                    return canapeDTO;
                })
                .collect(Collectors.toList());

        logger.info("Returning all canapes");
        return canapeDTOs;
    }
}
