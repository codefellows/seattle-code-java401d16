package com.cyberZork.salmonCookiesD16.repositories;

import com.cyberZork.salmonCookiesD16.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByName(String name);
}
