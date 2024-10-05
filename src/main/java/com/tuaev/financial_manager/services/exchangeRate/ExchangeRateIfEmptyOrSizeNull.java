package com.tuaev.financial_manager.services.exchangeRate;
import com.tuaev.financial_manager.entity.ExchangeRate;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public interface ExchangeRateIfEmptyOrSizeNull {
    List<ExchangeRate> ifEmptyOrSizeNull(List<ExchangeRate> exchangeRates) throws IOException, InterruptedException;
}
