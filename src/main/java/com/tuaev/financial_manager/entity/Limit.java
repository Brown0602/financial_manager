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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "datetime")
    private LocalDateTime dateTime;
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @Column(name = "category")
    private String category;
    @OneToMany(mappedBy = "limit")
    private List<Transaction> transactions;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @Column(name = "date_limit")
    private BigDecimal dateLimit;
}
