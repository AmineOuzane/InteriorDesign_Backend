package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.HabillageDTO;
import com.example.sketch20_backend.dto.MeubleDTO;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.model.Habillage;
import com.example.sketch20_backend.model.Meuble;
import com.example.sketch20_backend.service.HabillageService;
import com.example.sketch20_backend.service.MeubleService;
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
@RequestMapping("/habillages")
public class HabillageController {

    private static final Logger logger = LoggerFactory.getLogger(HabillageService.class);

    @Autowired
    private HabillageService habillageService;

    @GetMapping("/{id}")
    public HabillageDTO getHabillage(@PathVariable int id) {
        logger.info("Received request to get habillage with ID: {}", id);

        Optional<Habillage> habillage = habillageService.getHabillage(id);

        if (habillage.isPresent()) {
            Habillage p = habillage.get();
            logger.debug("habillage found: {}", p);

            String base64Image = Base64.getEncoder().encodeToString(p.getImage());
            HabillageDTO habillageDTO = ObjectMapperUtils.convertToDto(p);
            habillageDTO.setImage(base64Image);

            logger.info("Returning meuble DTO: {}", habillageDTO.toString());
            return habillageDTO;
        } else {
            logger.error("habillage with ID {} not found", id);
            throw new ProductNotFoundException("habillage not found");
        }
    }

    @GetMapping
    public List<HabillageDTO> getAllHabillages() {
        logger.info("Received request to get all habillage");

        List<Habillage> habillages = habillageService.getAllHabilages();
        List<HabillageDTO> habillageDTOS = habillages.stream()
                .map(habillage -> {
                    String base64Image = Base64.getEncoder().encodeToString(habillage.getImage());
                    HabillageDTO habillageDTO = ObjectMapperUtils.convertToDto(habillage);
                    habillageDTO.setImage(base64Image);
                    return habillageDTO;
                })
                .collect(Collectors.toList());

        logger.info("Returning all habillage");
        return habillageDTOS;
    }


}
