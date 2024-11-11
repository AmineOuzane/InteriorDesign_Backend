package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.CanapeDTO;
import com.example.sketch20_backend.dto.ChaiseDTO;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.model.Chaise;
import com.example.sketch20_backend.service.CanapeService;
import com.example.sketch20_backend.service.ChaiseService;
import com.example.sketch20_backend.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chaises")
public class ChaiseController {

    private static final Logger logger = LoggerFactory.getLogger(ChaiseService.class);

    @Autowired
    private ChaiseService chaiseService;

    @GetMapping("/{id}")
    public ChaiseDTO getChaise(@PathVariable int id) {
        logger.info("Received request to get chaise with ID: {}", id);

        Optional<Chaise> chaise = chaiseService.getChaise(id);

        if (chaise.isPresent()) {
            Chaise p = chaise.get();
            logger.debug("chaise found: {}", p);

            String base64Image = Base64.getEncoder().encodeToString(p.getImage());
            ChaiseDTO chaiseDTO = ObjectMapperUtils.convertToDto(p);
            chaiseDTO.setImage(base64Image);

            logger.info("Returning chaise DTO: {}", chaiseDTO.toString());
            return chaiseDTO;
        } else {
            logger.error("chaise with ID {} not found", id);
            throw new ProductNotFoundException("chaise not found");
        }
    }

    @GetMapping
    public List<ChaiseDTO> getAllChaises() {
        logger.info("Received request to get all chaises");

        List<Chaise> chaises = chaiseService.getAllChaises();
        List<ChaiseDTO> chaiseDTOS = chaises.stream()
                .map(chaise -> {
                    String base64Image = Base64.getEncoder().encodeToString(chaise.getImage());
                    ChaiseDTO chaiseDTO = ObjectMapperUtils.convertToDto(chaise);
                    chaiseDTO.setImage(base64Image);
                    return chaiseDTO;
                })
                .collect(Collectors.toList());

        logger.info("Returning all chaise");
        return chaiseDTOS;
    }

}
