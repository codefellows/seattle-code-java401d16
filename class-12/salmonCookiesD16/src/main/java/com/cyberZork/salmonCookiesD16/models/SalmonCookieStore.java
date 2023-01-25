package com.cyberZork.salmonCookiesD16.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// TODO: turn into Entity -> DB model
@Entity
public class SalmonCookieStore {

    // TODO: setup ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //IF you need longer than 255 characters, use these 2 annotations
    // @Lob
    // @Type(type = "org.hibernate.type.TextType")

    private String name;
    private Integer averageCookiesPerDay;

    // NEED THIS. A default PROTECTED constructor
    // Bcuz Spring uses this to generate the database table.
    protected SalmonCookieStore() {}

    public SalmonCookieStore(String name, Integer averageCookiesPerDay) {
        this.name = name;
        this.averageCookiesPerDay = averageCookiesPerDay;
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
