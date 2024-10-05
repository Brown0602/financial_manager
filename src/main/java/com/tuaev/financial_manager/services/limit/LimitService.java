package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.dto.LimitDTO;
import com.tuaev.financial_manager.entity.Limit;
import com.tuaev.financial_manager.repositories.LimitRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class LimitService implements LimitCreate, LimitGetAllLimits{

    private LimitRepo limitRepo;

    @Override
    public void create(LimitDTO limitDTO){
        Limit limit = new Limit();
        limit.setSum(limitDTO.getLimitSum());
        limit.setDateTime(LocalDateTime.now());
        limit.setCurrencyShortname(limitDTO.getLimitCurrencyShortname());
        limit.setCategory(limitDTO.getCategory());
        limitRepo.save(limit);
    }

    @Override
    public List<Limit> getAllLimits(){
        return null;
    }
}
