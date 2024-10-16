package com.tuaev.financial_manager.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "name")
    private String name;
    @Column( name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "client")
    private List<Limit> limits;
}
