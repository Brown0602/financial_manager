package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.dto.LimitDTO;
import org.springframework.stereotype.Service;

@Service
public interface LimitCreate {
    void create(LimitDTO limitDTO);
}
