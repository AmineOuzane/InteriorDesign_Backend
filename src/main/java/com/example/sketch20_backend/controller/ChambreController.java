package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.ChambreDTO;
import com.example.sketch20_backend.dto.ProductDTO;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.model.Chambre;
import com.example.sketch20_backend.model.Product;
import com.example.sketch20_backend.service.ChambreService;
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
@RequestMapping("/chambres")
public class ChambreController {
    private static final Logger logger = LoggerFactory.getLogger(ChambreController.class);

    @Autowired
    private ChambreService chambreService;

    @GetMapping("/{id}")
    public ChambreDTO getChambre(@PathVariable int id) {
        logger.info("Received request to get chambre with ID: {}", id);

        Optional<Chambre> chambre = chambreService.getChambre(id);

        if (chambre.isPresent()) {
            Chambre p = chambre.get();
            logger.debug("chambre found: {}", p);

            String base64Image = Base64.getEncoder().encodeToString(p.getImage());
            ChambreDTO chambreDTO = ObjectMapperUtils.convertToDto(p);
            chambreDTO.setImage(base64Image);

            logger.info("Returning chambre DTO: {}", chambreDTO);
            return chambreDTO;
        } else {
            logger.error("chambre with ID {} not found", id);
            throw new ProductNotFoundException("Product not found");
        }
    }

    @GetMapping
    public List<ChambreDTO> getAllChambres() {
        logger.info("Received request to get all chambres");

        List<Chambre> chambres = chambreService.getAllChambres();
        List<ChambreDTO> chambreDTOS = chambres.stream()
                .map(chambre -> {
                    String base64Image = Base64.getEncoder().encodeToString(chambre.getImage());
                    ChambreDTO chambreDTO = ObjectMapperUtils.convertToDto(chambre);
                    chambreDTO.setImage(base64Image);
                    return chambreDTO;
                })
                .collect(Collectors.toList());

        logger.info("Returning all chambres");
        return chambreDTOS;
    }
}
