package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.ExchangeRate;
import com.tuaev.financial_manager.entity.Limit;
import com.tuaev.financial_manager.entity.Transaction;
import com.tuaev.financial_manager.repositories.TransactionRepo;
import com.tuaev.financial_manager.services.exchange_rate.*;
import com.tuaev.financial_manager.services.limit.DefaultLimitService;
import com.tuaev.financial_manager.services.transaction.transaction_validator.Currency;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultTransactionService implements TransactionService {

    private GetCurrentExchangeRatesService getCurrentExchangeRatesService;
    private DefaultLimitService limitService;
    private TransactionRepo transactionRepo;

    private BigDecimal conversion(List<ExchangeRate> exchangeRates, BigDecimal sum, String shortnameCurrency) {
        for (ExchangeRate exchangeRate : exchangeRates) {
            if (exchangeRate.getCurrencyPair().equals(shortnameCurrency + "/" + Currency.CURRENCIES.getUSD())) {
                return new BigDecimal(String.valueOf(sum)).divide(exchangeRate.getClose(), 2, RoundingMode.HALF_UP);
            }
        }
        return null;
    }

    private Limit getLimitByCategory(TransactionDTO transactionDTO, List<ExchangeRate> exchangeRates) {
        Limit limit = limitService.lastLimitByCategory(transactionDTO);
        if (limit == null || !transactionDTO.getExpenseCategory().equals(limit.getCategory())) {
            return null;
        }
        BigDecimal remainsLimit = new BigDecimal(String.valueOf(limit.getSum())).subtract(
                conversion(exchangeRates, transactionDTO.getSum(), transactionDTO.getCurrencyShortname()));
        limit.setSum(remainsLimit);
        limitService.updateLimit(limit);
        return limit;

    }

    private Boolean limitFlag(Limit limit) {
        return limit.getSum().signum() != 1;
    }

    private Transaction createTransaction(TransactionDTO transactionDTO, List<ExchangeRate> exchangeRates){
        Transaction transaction = new Transaction();
        transaction.setAccountFrom(transactionDTO.getAccountFrom());
        transaction.setAccountTo(transactionDTO.getAccountTo());
        transaction.setCurrencyShortname(transactionDTO.getCurrencyShortname());
        transaction.setSum(conversion(exchangeRates, transactionDTO.getSum(), transactionDTO.getCurrencyShortname()));
        transaction.setExpenseCategory(transactionDTO.getExpenseCategory());
        transaction.setDatetime(LocalDateTime.now());
        transaction.setLimit(getLimitByCategory(transactionDTO, exchangeRates));
        transaction.setLimitExceeded(limitFlag(transaction.getLimit()));
        return transaction;
    }


    @Transactional
    @Override
    public void saveTransaction(TransactionDTO transactionDTO) throws IOException, InterruptedException {
        List<ExchangeRate> exchangeRates = getCurrentExchangeRatesService.getCurrentExchangeRates();
        Transaction transaction = createTransaction(transactionDTO, exchangeRates);
        transactionRepo.save(transaction);
    }

    @Override
    public List<Object[]> getAllTransactionsExceededLimitTrue() {
        return transactionRepo.transactionsExceededLimitTrue();
    }
}
