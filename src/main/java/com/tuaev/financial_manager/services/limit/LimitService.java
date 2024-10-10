package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.dto.LimitDTO;
import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.Limit;
import java.util.List;

public interface LimitService {
    void save(LimitDTO limitDTO);
    void updateLimit(Limit limit);
    List<Limit> getAllLimits();
    void setDefaultLimit(TransactionDTO transactionDTO);
}
