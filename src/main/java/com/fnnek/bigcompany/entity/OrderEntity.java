package com.fnnek.bigcompany.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

    public OrderEntity(String name, BigDecimal amount, LocalDateTime date, ClientEntity client, CompanyEntity company) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.client = client;
        this.company = company;
    }

    public OrderEntity() {
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private CompanyEntity company;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ClientEntity getClient() {
        return client;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    

}
