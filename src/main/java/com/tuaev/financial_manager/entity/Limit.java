package com.tuaev.financial_manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "limits")
public class Limit {

    @OneToMany(mappedBy = "limit")
    private List<Transaction> transactions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "limit_sum")
    private BigDecimal Sum;
    @Column(name = "limit_datetime")
    private LocalDateTime dateTime;
    @Column(name = "limit_currency_shortname")
    private String currencyShortname;
    @Column(name = "category")
    private String category;
}
