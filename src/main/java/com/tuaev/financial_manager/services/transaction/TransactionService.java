package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import java.io.IOException;
import java.util.List;

public interface TransactionService {
    void saveTransaction(TransactionDTO transactionDTO) throws IOException, InterruptedException;
    List<Object[]> getAllTransactionsExceededLimitTrue();
}
