package com.tuaev.financial_manager.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class LimitDTO {
    private BigDecimal sum;
    private LocalDateTime dateTime;
    private String currencyShortname;
    private String category;
    private Long account;
}
