package com.tuaev.financial_manager.services.exchangeRate;

import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface ExchangeRateSave{
    void save() throws IOException, InterruptedException;
}
