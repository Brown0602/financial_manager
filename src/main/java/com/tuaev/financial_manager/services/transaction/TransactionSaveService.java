package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import java.io.IOException;

public interface TransactionSaveService {
    void saveTransaction(TransactionDTO transactionDTO) throws IOException, InterruptedException;
}
