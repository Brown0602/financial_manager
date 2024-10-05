package com.tuaev.financial_manager.services.limit;

import com.tuaev.financial_manager.entity.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LimitGetAllLimits {
    List<Limit> getAllLimits();
}
