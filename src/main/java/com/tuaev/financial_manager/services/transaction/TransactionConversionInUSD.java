package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.entity.ExchangeRate;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionConversionInUSD {
    BigDecimal conversion(List<ExchangeRate> exchangeRates, BigDecimal sum, String shortnameCurrency);
}
