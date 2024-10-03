package com.tuaev.financial_manager.controllers;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionalController {

    private TransactionService transactionService;

    @PostMapping("/transaction")
    public String saveTransaction(@RequestBody TransactionDTO transactionDTO){
        return transactionService.saveTransaction(transactionDTO);
    }

    @GetMapping("/transactions")
    public void getTransactions(){

    }

    @GetMapping("/transactions/exceeded")
    public void getTransactionsLimit(){

    }

}
