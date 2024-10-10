package com.tuaev.financial_manager.services.exchange_rate;

import com.tuaev.financial_manager.entity.ExchangeRate;
import java.io.IOException;
import java.util.List;

public interface GetCurrentExchangeRatesService {
    List<ExchangeRate> getCurrentExchangeRates() throws IOException, InterruptedException;
}
