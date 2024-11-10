package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.Chambre;
import com.example.sketch20_backend.model.Table;
import com.example.sketch20_backend.repository.ChambreRepository;
import com.example.sketch20_backend.repository.TableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableService {
    private static final Logger logger = LoggerFactory.getLogger(TableService.class);
    @Autowired
    TableRepository tableRepository;

    public Optional<Table> getTable(int id) {
        logger.info("Fetching table with ID: {}", id);
        Optional<Table> table = tableRepository.findById(id);
        if (table.isPresent()) {
            logger.debug("table found: {}", table.get());
        } else {
            logger.warn("table not found with ID: {}", id);
        }
        return table;
    }

    public List<Table> getAllTables() {
        logger.info("Fetching all tables");
        List<Table> tables = tableRepository.findAll()
                .stream()
                .filter(table -> table != null)
                .collect(Collectors.toList());
        logger.info("Number of chambres found: {}", tables.size());
        return tables;
    }

}
