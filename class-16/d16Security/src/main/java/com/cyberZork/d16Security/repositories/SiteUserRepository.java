package com.cyberZork.d16Security.repositories;

import com.cyberZork.d16Security.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO Step 1: Create SiteUSer Repo
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    public SiteUser findByUsername(String username);
}
