package com.tuaev.financial_manager.services.transaction.transaction_validator;

import com.tuaev.financial_manager.entity.ExchangeRate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionValidatorService implements Validation {

    @Override
    public Boolean isCurrencyCorrect(List<ExchangeRate> exchangeRates, String shortnameCurrency) {
        return exchangeRates.stream().anyMatch(exchangeRate ->
                exchangeRate.getCurrencyPair().equals(shortnameCurrency + "/" + Currency.CURRENCIES.getUSD()));
    }

    @Override
    public Boolean isCategoryCorrect(String category) {
        return category.equals(Category.CATEGORIES.getProducts()) || category.equals(Category.CATEGORIES.getServices());
    }
}
