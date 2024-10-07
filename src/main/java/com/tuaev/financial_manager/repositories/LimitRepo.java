package com.tuaev.financial_manager.repositories;

import com.tuaev.financial_manager.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LimitRepo extends JpaRepository<Limit, Long> {
    @Query(value = "SELECT * FROM limits WHERE category = :category ORDER BY limits.limit_datetime DESC LIMIT 1", nativeQuery = true)
    Optional<Limit> lastLimitByCategory(@Param("category") String category);
}
