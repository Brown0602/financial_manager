package com.tuaev.financial_manager.controllers;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.services.exchange_rate.GetCurrentExchangeRatesService;
import com.tuaev.financial_manager.services.transaction.TransactionService;
import com.tuaev.financial_manager.services.transaction.transaction_validator.Validation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionalController {

    private GetCurrentExchangeRatesService getCurrentExchangeRatesService;
    private Validation validation;
    private TransactionService transactionSaveService;

    @PostMapping("/transaction")
    public String saveTransaction(@RequestBody TransactionDTO transactionDTO) throws IOException, InterruptedException {
        if (
                Boolean.TRUE.equals(!validation.isCurrencyCorrect(getCurrentExchangeRatesService.getCurrentExchangeRates(), transactionDTO.getCurrencyShortname()))
                || Boolean.TRUE.equals(!validation.isCategoryCorrect(transactionDTO.getExpenseCategory()))
            ){
            return "Ошибка! Неверная валюта или неверная категория транзакции!";
        }
        transactionSaveService.saveTransaction(transactionDTO);
        return "";
    }

    @GetMapping("/transactions/exceeded")
    public List<Object[]> getTransactionsLimit(){
        return transactionSaveService.getAllTransactionsExceededLimitTrue();
    }
}
