package com.example.sketch20_backend.repository;

import com.example.sketch20_backend.model.My_Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<My_Table, Integer> {
}
