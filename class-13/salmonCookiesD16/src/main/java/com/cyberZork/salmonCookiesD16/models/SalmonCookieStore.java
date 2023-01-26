package com.cyberZork.salmonCookiesD16.models;


import jakarta.persistence.*;

import java.util.List;


// TODO: turn into Entity -> DB model
@Entity
public class SalmonCookieStore {

    // TODO: setup ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private Integer averageCookiesPerDay;

    // QUERY the Employees table, return all Employees belonging to this store.
    // 1:many
    @OneToMany(mappedBy = "storeWorkingAt")
    private List<Employee> employees;

    protected SalmonCookieStore() {}

    public SalmonCookieStore(String name, Integer averageCookiesPerDay) {
        this.name = name;
        this.averageCookiesPerDay = averageCookiesPerDay;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public Integer getAverageCookiesPerDay() {
        return averageCookiesPerDay;
    }

    public void setAverageCookiesPerDay(Integer averageCookiesPerDay) {
        this.averageCookiesPerDay = averageCookiesPerDay;
    }

    @Override
    public String toString() {
        return "This store is named: " + name;
    }
}
