package com.example.interactionapi.repository;

import com.example.interactionapi.domain.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BuyRepository extends JpaRepository<Buy, Integer> {
    Optional<Buy> findOrderByName(String orderName);
}
