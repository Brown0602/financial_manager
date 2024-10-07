package com.tuaev.financial_manager.services.transaction.transaction_validator;

import com.tuaev.financial_manager.entity.ExchangeRate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionValidator implements TransactionValidatorIsCorrectCurrency, TransactionValidatorIsCategoryCorrect {

    @Override
    public Boolean isCorrectCurrency(List<ExchangeRate> exchangeRates, String shortnameCurrency) {
        return exchangeRates.stream().anyMatch(exchangeRate ->
                exchangeRate.getCurrencyPair().equals(shortnameCurrency + "/USD"));
    }

    @Override
    public Boolean isCategoryCorrect(String category) {
        return category.equals("Товары") || category.equals("Услуги");
    }
}
