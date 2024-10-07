package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.entity.Limit;
import org.springframework.stereotype.Service;

@Service
public interface LimitServiceUpdateLimit {
    void updateLimit(Limit limit);
}
