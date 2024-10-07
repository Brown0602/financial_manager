package com.tuaev.financial_manager.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class LimitDTO {
    private BigDecimal limitSum;
    private LocalDateTime dateTime;
    private String limitCurrencyShortname;
    private String category;
}
