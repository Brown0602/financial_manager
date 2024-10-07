package com.tuaev.financial_manager.controllers;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.services.exchange_rate.ExchangeRateServiceGetCurrentExchangeRates;
import com.tuaev.financial_manager.services.transaction.TransactionServiceSaveTransaction;
import com.tuaev.financial_manager.services.transaction.transaction_validator.TransactionValidatorIsCategoryCorrect;
import com.tuaev.financial_manager.services.transaction.transaction_validator.TransactionValidatorIsCorrectCurrency;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class TransactionalController {

    private ExchangeRateServiceGetCurrentExchangeRates exchangeRateServiceGetCurrentExchangeRates;
    private TransactionValidatorIsCorrectCurrency transactionValidatorIsCorrectCurrency;
    private TransactionValidatorIsCategoryCorrect transactionValidatorIsCategoryCorrect;
    private TransactionServiceSaveTransaction transactionServiceSaveTransaction;

    @PostMapping("/transaction")
    public String saveTransaction(@RequestBody TransactionDTO transactionDTO) throws IOException, InterruptedException {
        if (
                !transactionValidatorIsCorrectCurrency.isCorrectCurrency(exchangeRateServiceGetCurrentExchangeRates.getCurrentExchangeRates(), transactionDTO.getCurrencyShortname())
                ||
                !transactionValidatorIsCategoryCorrect.isCategoryCorrect(transactionDTO.getExpenseCategory())
            ){
            return "Ошибка! Неверная валюта или неверная категория транзакции!";
        }
        transactionServiceSaveTransaction.saveTransaction(transactionDTO);
        return "";
    }

    @GetMapping("/transactions/exceeded")
    public void getTransactionsLimit(){

    }

}
