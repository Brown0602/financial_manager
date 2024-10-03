package com.tuaev.financial_manager.repositories;

import com.tuaev.financial_manager.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepo extends JpaRepository<ExchangeRate, Long> {
}
