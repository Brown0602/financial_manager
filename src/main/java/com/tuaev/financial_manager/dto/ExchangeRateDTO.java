package com.tuaev.financial_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

@Setter
@Getter
public class ExchangeRateDTO {
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private Map<String, CurrencyData> data;
    @Getter
    @Setter
    public static class Meta{
        @JsonProperty("last_updated_at")
        private OffsetDateTime lastUpdateAt;
    }
    @Getter
    @Setter
    public static class CurrencyData{
        @JsonProperty("code")
        private String code;
        @JsonProperty("value")
        private BigDecimal value;
    }
}
