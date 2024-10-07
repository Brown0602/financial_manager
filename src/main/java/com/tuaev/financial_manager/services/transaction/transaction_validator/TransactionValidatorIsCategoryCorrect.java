package com.tuaev.financial_manager.services.transaction.transaction_validator;

import org.springframework.stereotype.Service;

@Service
public interface TransactionValidatorIsCategoryCorrect {
    Boolean isCategoryCorrect(String category);
}
