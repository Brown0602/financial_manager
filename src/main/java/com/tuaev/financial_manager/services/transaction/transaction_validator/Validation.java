package com.tuaev.financial_manager.services.transaction.transaction_validator;

import com.tuaev.financial_manager.entity.ExchangeRate;
import java.util.List;

public interface Validation {
    Boolean isCategoryCorrect(String category);
    Boolean isCurrencyCorrect(List<ExchangeRate> exchangeRates, String shortnameCurrency);
}
