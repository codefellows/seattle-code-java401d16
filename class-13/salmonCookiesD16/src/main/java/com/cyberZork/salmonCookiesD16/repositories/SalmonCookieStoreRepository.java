package com.cyberZork.salmonCookiesD16.repositories;

import com.cyberZork.salmonCookiesD16.models.SalmonCookieStore;
import org.springframework.data.jpa.repository.JpaRepository;

// make a repo for the data value. THIS IS A SERVICE - Singelton design principle == SPRING BEAN!!
// TODO: Turn into a JPA Repo
public interface SalmonCookieStoreRepository extends JpaRepository<SalmonCookieStore, Long> {
    // The reason we are using an interface, is so we can create CUSTOM CRUD queries

    // DARK MAGIC that we made happen with a specific function name
    public SalmonCookieStore findByName(String name);
    public SalmonCookieStore findByAverageCookiesPerDay(Integer averageCookiesPerDay);
}
