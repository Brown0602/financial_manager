package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.dto.LimitDTO;
import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.Limit;
import com.tuaev.financial_manager.repositories.LimitRepo;
import com.tuaev.financial_manager.services.transaction.transaction_validator.Currency;
import lombok.AllArgsConstructor;
import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class DefaultLimitService implements LimitService{

    private LimitRepo limitRepo;

    @Override
    public void updateLimit(Limit limit){
        limitRepo.save(limit);
    }

    private Limit createLimit(LimitDTO limitDTO){
        Limit limit = new Limit();
        limit.setSum(limitDTO.getSum());
        limit.setDateTime(LocalDateTime.now());
        limit.setCurrencyShortname(limitDTO.getCurrencyShortname());
        limit.setCategory(limitDTO.getCategory());
        return limit;
    }

    @Override
    public  void setDefaultLimit(TransactionDTO transactionDTO){
        Limit limit = new Limit();
        limit.setSum(BigDecimal.valueOf(1000));
        limit.setDateTime(LocalDateTime.now());
        limit.setCurrencyShortname(Currency.CURRENCIES.getUSD());
        limit.setCategory(transactionDTO.getExpenseCategory());
        limitRepo.save(limit);
    }

    public Limit lastLimitByCategory(TransactionDTO transactionDTO){
        Optional<Limit> limitOptional = limitRepo.lastLimitByCategory(transactionDTO.getExpenseCategory());
        return limitOptional.orElse(null);
    }

    @Override
    public void save(LimitDTO limitDTO){
        Limit limit = createLimit(limitDTO);
        limitRepo.save(limit);
    }

    @Override
    public List<Limit> getAllLimits(){
        return limitRepo.findAll();
    }

}
