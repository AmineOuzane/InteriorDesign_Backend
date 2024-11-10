package com.example.sketch20_backend.controller;

import com.example.sketch20_backend.dto.CanapeDTO;
import com.example.sketch20_backend.dto.TableDTO;
import com.example.sketch20_backend.exception.ProductNotFoundException;
import com.example.sketch20_backend.model.Canape;
import com.example.sketch20_backend.model.Table;
import com.example.sketch20_backend.service.CanapeService;
import com.example.sketch20_backend.service.TableService;
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
@RequestMapping("/tables")
public class TableController {
    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private TableService tableService;

    @GetMapping("/{id}")
    public TableDTO getTable(@PathVariable int id) {
        logger.info("Received request to get table with ID: {}", id);

        Optional<Table> table = tableService.getTable(id);

        if (table.isPresent()) {
            Table p = table.get();
            logger.debug("Table found: {}", p);

            String base64Image = Base64.getEncoder().encodeToString(p.getImage());
            TableDTO tableDTO = ObjectMapperUtils.convertToDto(p);
            tableDTO.setImage(base64Image);

            logger.info("Returning tableDTO: {}", tableDTO);
            return tableDTO;
        } else {
            logger.error("Table with ID {} not found", id);
            throw new ProductNotFoundException("table not found");
        }
    }

    // New method to get all canapes
    @GetMapping
    public List<TableDTO> getAllTables() {
        logger.info("Received request to get all tables");

        List<Table> tables = tableService.getAllTables();
        List<TableDTO> tableDTOS = tables.stream()
                .map(table -> {
                    String base64Image = Base64.getEncoder().encodeToString(table.getImage());
                    TableDTO tableDTO = ObjectMapperUtils.convertToDto(table);
                    tableDTO.setImage(base64Image);
                    return tableDTO;
                })
                .collect(Collectors.toList());

        logger.info("Returning all canapes");
        return tableDTOS;
    }
}
