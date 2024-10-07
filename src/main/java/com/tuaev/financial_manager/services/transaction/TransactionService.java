package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.ExchangeRate;
import com.tuaev.financial_manager.entity.Limit;
import com.tuaev.financial_manager.entity.Transaction;
import com.tuaev.financial_manager.repositories.TransactionRepo;
import com.tuaev.financial_manager.services.exchangeRate.*;
import com.tuaev.financial_manager.services.limit.LimitService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.DialectOverride;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService implements TransactionSave, TransactionIsCorrectCurrency, TransactionConversionInUSD, TransactionIsCategoryCorrect, TransactionSettingLimitByCategory{

    private ExchangeRateCheckingTheCorrectDate exchangeRateCheckingTheCorrectDate;
    private LimitService limitService;
    private TransactionRepo transactionRepo;
    private List<ExchangeRate> exchangeRates;
    private ExchangeRateIfEmptyOrSizeNull exchangeRateIfEmptyOrSizeNull;

    @Override
    public Boolean isCorrectCurrency(List<ExchangeRate> exchangeRates, String shortnameCurrency) {
        return exchangeRates.stream().anyMatch(exchangeRate ->
                exchangeRate.getCurrencyPair().equals(shortnameCurrency + "/USD"));
    }

    @Override
    public BigDecimal conversion(List<ExchangeRate> exchangeRates, BigDecimal sum, String shortnameCurrency) {
        for (ExchangeRate exchangeRate:exchangeRates) {
            if (exchangeRate.getCurrencyPair().equals(shortnameCurrency + "/USD")) {
                return new BigDecimal(String.valueOf(sum)).divide(exchangeRate.getClose(), 2, RoundingMode.HALF_UP);
            }
        }
        return null;
    }

    @Override
    public Transaction settingLimitByCategory(TransactionDTO transactionDTO, Transaction transaction){
        Limit limit = limitService.lastLimitByCategory(transactionDTO);
            if (transactionDTO.getExpenseCategory().equals(limit.getCategory())){
                BigDecimal remainsLimit = new BigDecimal(String.valueOf(limit.getSum())).subtract(conversion(exchangeRates, transactionDTO.getSum(), transactionDTO.getCurrencyShortname()));
                limit.setSum(remainsLimit);
                limitService.save(limit);
                if (limit.getSum().signum() == -1){
                    transaction.setLimit(null);
                }
                if (limit.getSum().signum() != -1){
                    transaction.setLimit(limit);
                    transaction.setLimitExceeded(false);
                }
                if (limit.getSum().signum() == -1 && transaction.getLimit() == null){
                    transaction.setLimitExceeded(null);
                }
            }
        return transaction;
    }

    @Transactional
    @Override
    public String save(@RequestBody TransactionDTO transactionDTO) throws IOException, InterruptedException {
        exchangeRates = exchangeRateIfEmptyOrSizeNull.ifEmptyOrSizeNull(exchangeRates);
        exchangeRates = exchangeRateCheckingTheCorrectDate.checkingTheCorrectDate(exchangeRates);
        Transaction transaction = new Transaction();
        transaction = settingLimitByCategory(transactionDTO, transaction);
        transaction.setAccountFrom(transactionDTO.getAccountFrom());
        transaction.setAccountTo(transactionDTO.getAccountTo());
        if (isCorrectCurrency(exchangeRates, transactionDTO.getCurrencyShortname())){
            transaction.setCurrencyShortname(transactionDTO.getCurrencyShortname());
        }else {
            return "Неправильная валюта";
        }
        transaction.setSum(conversion(exchangeRates, transactionDTO.getSum(), transactionDTO.getCurrencyShortname()));
        transaction.setDatetime(LocalDateTime.now());
        if (isCategoryCorrect(transactionDTO.getExpenseCategory())) {
            transaction.setExpenseCategory(transactionDTO.getExpenseCategory());
        }else {
            return "Такой категории нет";
        }
        transactionRepo.save(transaction);
        return "";
    }

    @Override
    public Boolean isCategoryCorrect(String category) {
        return category.equals("Товары") || category.equals("Услуги");
    }
}
