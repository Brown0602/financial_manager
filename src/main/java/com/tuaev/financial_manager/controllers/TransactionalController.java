package com.tuaev.financial_manager.controllers;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.services.transaction.TransactionSave;
import com.tuaev.financial_manager.services.transaction.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class TransactionalController {

    private TransactionSave transactionSave;

    @PostMapping("/transaction")
    public String saveTransaction(@RequestBody TransactionDTO transactionDTO) throws IOException, InterruptedException {
        return transactionSave.save(transactionDTO);
    }

    @GetMapping("/transactions/exceeded")
    public void getTransactionsLimit(){

    }

}
