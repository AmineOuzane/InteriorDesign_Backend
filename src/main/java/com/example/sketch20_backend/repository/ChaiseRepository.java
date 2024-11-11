package com.example.sketch20_backend.repository;

import com.example.sketch20_backend.model.Chaise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaiseRepository extends JpaRepository<Chaise, Integer> {
}
