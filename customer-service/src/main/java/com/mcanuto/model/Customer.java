package com.mcanuto.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Entity
@Table(name="customer")
@Access(AccessType.FIELD)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private BigDecimal credit;

    @ElementCollection
    private Map<Long, BigDecimal> creditReservations;

    @Version
    private Long version;

    public Customer() {
    }

    public Customer(String email, String name, BigDecimal credit) {
        this.email = email;
        this.name = name;
        this.credit = credit;
        this.creditReservations = Collections.emptyMap();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public Map<Long, BigDecimal> getCreditReservations() {
        return creditReservations;
    }
}
