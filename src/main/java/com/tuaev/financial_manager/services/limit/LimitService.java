package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.dto.LimitDTO;
import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.Limit;
import com.tuaev.financial_manager.repositories.LimitRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class LimitService implements LimitServiceSaveLimit, LimitGetAllLimits, LimitServiceUpdateLimit{

    private LimitRepo limitRepo;

    @Override
    public void updateLimit(Limit limit){
        limitRepo.save(limit);
    }

    private Limit createLimit(LimitDTO limitDTO){
        Limit limit = new Limit();
        limit.setSum(limitDTO.getLimitSum());
        limit.setDateTime(LocalDateTime.now());
        limit.setCurrencyShortname(limitDTO.getLimitCurrencyShortname());
        limit.setCategory(limitDTO.getCategory());
        return limit;
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
        return null;
    }

}
