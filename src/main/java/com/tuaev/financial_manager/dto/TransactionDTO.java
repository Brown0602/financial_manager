package com.tuaev.financial_manager.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class TransactionDTO {
    private Long accountFrom;
    private Long accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private String expenseCategory;
    private LocalDate datetime;
}
