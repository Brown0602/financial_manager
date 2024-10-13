package com.tuaev.financial_manager.repositories;

import com.tuaev.financial_manager.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t.*, l.date_limit, l.currency_shortname, l.datetime FROM transactions t" +
            " LEFT JOIN limits l ON t.limit_id = l.id WHERE t.exceeded = true", nativeQuery = true)
    List<Object[]> transactionsExceededLimitTrue();
}
