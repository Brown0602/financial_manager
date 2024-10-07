package com.tuaev.financial_manager.services.transaction;

import com.tuaev.financial_manager.dto.TransactionDTO;
import com.tuaev.financial_manager.entity.Transaction;

public interface TransactionSettingLimitByCategory {
    Transaction settingLimitByCategory(TransactionDTO transactionDTO, Transaction transaction);
}
