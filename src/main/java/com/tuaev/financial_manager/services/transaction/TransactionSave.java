package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Service
public interface TransactionSave {
    String save(@RequestBody TransactionDTO transactionDTO) throws IOException, InterruptedException;
}
