package com.example.sketch20_backend.service;

import com.example.sketch20_backend.model.My_Table;
import com.example.sketch20_backend.model.Product;
import com.example.sketch20_backend.repository.TableRepository;
import jakarta.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableService {
    private static final Logger logger = LoggerFactory.getLogger(TableService.class);
    @Autowired
    TableRepository tableRepository;

    public Optional<My_Table> getTable(int id) {
        logger.info("Fetching table with ID: {}", id);
        Optional<My_Table> table = tableRepository.findById(id);
        if (table.isPresent()) {
            logger.debug("table found: {}", table.get());
        } else {
            logger.warn("table not found with ID: {}", id);
        }
        return table;
    }

    public List<My_Table> getAllTables() {
        logger.info("Fetching all products");
        List<My_Table> tables = tableRepository.findAll();
        logger.info("Number of products found: {}", tables.size());
        return tables;
    }
}
