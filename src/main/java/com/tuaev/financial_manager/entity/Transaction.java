package com.tuaev.financial_manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @ManyToOne
    @JoinColumn(name = "limit_id")
    private Limit limit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_from")
    private Long accountFrom;
    @Column(name = "account_to")
    private Long accountTo;
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "expense_category")
    private String expenseCategory;
    @Column(name = "datetime")
    private LocalDateTime datetime;
    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;

}
