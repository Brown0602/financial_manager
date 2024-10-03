package com.tuaev.financial_manager.services;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.ExchangeRate;
import com.tuaev.financial_manager.entity.Transaction;
import com.tuaev.financial_manager.repositories.TransactionRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService {

    private TransactionRepo transactionRepo;
    private ExchangeRateService exchangeRateService;
    private List<ExchangeRate> exchangeRates;
    public Iterable<Transaction> getTransactionsExceededLimit(){
        return null;
    }

    @Transactional
    public String saveTransaction(@RequestBody TransactionDTO transactionDTO){
        if (exchangeRates.isEmpty()){
            exchangeRates = exchangeRateService.getAllExchangeRates();
        }
        Transaction transaction = new Transaction();
        transaction.setAccount_from(transactionDTO.getAccount_from());
        transaction.setAccount_to(transactionDTO.getAccount_to());
        if (exchangeRates.stream().anyMatch(exchangeRate ->
                exchangeRate.getCurrencyPair().equals(transactionDTO.getCurrency_shortname() + "/USD"))) {
            transaction.setCurrency_shortname(transactionDTO.getCurrency_shortname());
            for (ExchangeRate exchangeRate : exchangeRates) {
                if (exchangeRate.getCurrencyPair().equals(transactionDTO.getCurrency_shortname() + "/USD")){
                    BigDecimal sum = new BigDecimal(String.valueOf(transactionDTO.getSum())).divide(exchangeRate.getClose(), 2, RoundingMode.HALF_UP);
                    transaction.setSum(sum);
                }
            }
        }else {
            return "Неправильная валюта";
        }
        transaction.setDatetime(LocalDateTime.now());
        if (transactionDTO.getExpense_category().equals("Товары") || transactionDTO.getExpense_category().equals("Услуги")) {
            transaction.setExpense_category(transactionDTO.getExpense_category());
        }else {
            return "Такой категории нет";
        }
        transactionRepo.save(transaction);
        return "";
    }

    public Iterable<Transaction> getAllTransactions(){
        return transactionRepo.findAll();
    }




}
