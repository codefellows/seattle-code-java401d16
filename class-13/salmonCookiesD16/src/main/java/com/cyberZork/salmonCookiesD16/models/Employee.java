package com.cyberZork.salmonCookiesD16.models;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private Double payPerHour;
    @ManyToOne
    private SalmonCookieStore storeWorkingAt;

    protected Employee() {}

    public Employee(String name, Double payPerHour, SalmonCookieStore storeWorkingAt) {
        this.name = name;
        this.payPerHour = payPerHour;
        this.storeWorkingAt = storeWorkingAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(Double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public SalmonCookieStore getStoreWorkingAt() {
        return storeWorkingAt;
    }

    public void setStoreWorkingAt(SalmonCookieStore storeWorkingAt) {
        this.storeWorkingAt = storeWorkingAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", payPerHour=" + payPerHour +
                ", storeWorkingAt=" + storeWorkingAt +
                '}';
    }
}
