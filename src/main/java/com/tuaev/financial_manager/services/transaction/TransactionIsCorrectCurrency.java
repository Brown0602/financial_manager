package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.entity.ExchangeRate;

import java.util.List;

public interface TransactionIsCorrectCurrency {
    Boolean isCorrectCurrency(List<ExchangeRate> exchangeRates, String shortnameCurrency);
}
