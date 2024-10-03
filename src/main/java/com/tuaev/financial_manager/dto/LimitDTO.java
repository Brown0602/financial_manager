package com.tuaev.financial_manager.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class LimitDTO {

    private Long userId;
    private String category;
    private BigDecimal amount;
    private LocalDate date;

}
