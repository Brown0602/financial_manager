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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long account_from;
    private Long account_to;
    private String currency_shortname;
    private BigDecimal sum;
    private String expense_category;
    private LocalDateTime datetime;
    private boolean limit_exceeded;

}
