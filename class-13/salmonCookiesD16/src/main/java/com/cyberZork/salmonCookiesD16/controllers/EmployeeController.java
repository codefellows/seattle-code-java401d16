package com.cyberZork.salmonCookiesD16.controllers;

import com.cyberZork.salmonCookiesD16.models.Employee;
import com.cyberZork.salmonCookiesD16.models.SalmonCookieStore;
import com.cyberZork.salmonCookiesD16.repositories.EmployeeRepository;
import com.cyberZork.salmonCookiesD16.repositories.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EmployeeController {
    // TODO Autowire your REPO!
    @Autowired
    SalmonCookieStoreRepository salmonCookieStoreRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    // Get route to get ALL employees

    // Get route to get ONE employee
    @GetMapping("/employee/{id}")
    public String getOneEmployee(@PathVariable Long id){
        // query DB, find emplyee by id
        Employee returnedEmployee = employeeRepository.findById(id).orElseThrow();
        // pass it to our view
        return "employee.html";
    }

    // Post route to create an employee
    @PostMapping("/employee")
    public RedirectView createAnEmployee(String name, Double payPerHour, String storeName){
        // query my DB for a Store with that name
        SalmonCookieStore storeWorkingAt = salmonCookieStoreRepository.findByName(storeName);
        // make a new employee
        Employee newEmployee = new Employee(name, payPerHour, storeWorkingAt);
        // save to DB
        employeeRepository.save(newEmployee);
        // TODO redirect somewhere
        return new RedirectView("/stores");
    }

    // Put route to update ONE employee

    // DELETE route to Delete ONE employee

}
