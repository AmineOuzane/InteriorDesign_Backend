package com.example.sketch20_backend.repository;

import com.example.sketch20_backend.model.Meuble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeubleRepository extends JpaRepository<Meuble, Integer> {
}
