package com.tuaev.financial_manager.services.exchange_rate;

import com.tuaev.financial_manager.entity.ExchangeRate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExchangeRateServiceGetCurrentExchangeRates {
    List<ExchangeRate> getCurrentExchangeRates() throws IOException, InterruptedException;
}
