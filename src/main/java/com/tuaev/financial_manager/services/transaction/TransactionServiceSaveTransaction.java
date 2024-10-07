package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface TransactionServiceSaveTransaction {
    void saveTransaction(TransactionDTO transactionDTO) throws IOException, InterruptedException;
}
