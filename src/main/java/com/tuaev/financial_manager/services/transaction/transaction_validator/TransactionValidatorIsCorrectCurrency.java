package com.tuaev.financial_manager.services.transaction.transaction_validator;

import com.tuaev.financial_manager.entity.ExchangeRate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TransactionValidatorIsCorrectCurrency {
    Boolean isCorrectCurrency(List<ExchangeRate> exchangeRates, String shortnameCurrency);
}
