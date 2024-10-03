package com.tuaev.financial_manager.repositories;

import com.tuaev.financial_manager.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepo extends JpaRepository<Limit, Long> {
}
