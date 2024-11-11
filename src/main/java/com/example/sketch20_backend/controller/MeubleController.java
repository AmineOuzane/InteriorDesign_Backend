package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.ChaiseDTO;
import com.example.sketch20_backend.dto.MeubleDTO;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.model.Chaise;
import com.example.sketch20_backend.model.Meuble;
import com.example.sketch20_backend.service.ChaiseService;
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
@RequestMapping("/meubles")
public class MeubleController {

        private static final Logger logger = LoggerFactory.getLogger(MeubleService.class);

        @Autowired
        private MeubleService meubleService;

        @GetMapping("/{id}")
        public MeubleDTO getMeuble(@PathVariable int id) {
            logger.info("Received request to get meuble with ID: {}", id);

            Optional<Meuble> meuble = meubleService.getMeuble(id);

            if (meuble.isPresent()) {
                Meuble p = meuble.get();
                logger.debug("meuble found: {}", p);

                String base64Image = Base64.getEncoder().encodeToString(p.getImage());
                MeubleDTO meubleDTO = ObjectMapperUtils.convertToDto(p);
                meubleDTO.setImage(base64Image);

                logger.info("Returning meuble DTO: {}", meubleDTO.toString());
                return meubleDTO;
            } else {
                logger.error("meuble with ID {} not found", id);
                throw new ProductNotFoundException("meuble not found");
            }
        }

        @GetMapping
        public List<MeubleDTO> getAllMeubles() {
            logger.info("Received request to get all meuble");

            List<Meuble> meubles = meubleService.getAllMeubles();
            List<MeubleDTO> meubleDTOS = meubles.stream()
                    .map(meuble -> {
                        String base64Image = Base64.getEncoder().encodeToString(meuble.getImage());
                        MeubleDTO meubleDTO = ObjectMapperUtils.convertToDto(meuble);
                        meubleDTO.setImage(base64Image);
                        return meubleDTO;
                    })
                    .collect(Collectors.toList());

            logger.info("Returning all meuble");
            return meubleDTOS;
        }

}
