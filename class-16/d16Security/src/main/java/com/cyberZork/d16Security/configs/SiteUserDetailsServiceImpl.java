package com.cyberZork.d16Security.configs;

import com.cyberZork.d16Security.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// TODO Step 3: UserDetailsServiceImpl implements UserDetailsService
//TODO: use the @Service here
@Service // Spring autodetects and loads
public class SiteUserDetailsServiceImpl implements UserDetailsService {
   // TODO Step 3a: Autowire SiteUSerRepository
    @Autowired
    SiteUserRepository siteUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Add a sout!
        System.out.println("SITE USER DETAILS SERVICE CALLS FOR USER(FROM database)");
        return (UserDetails) siteUserRepository.findByUsername(username);
    }
}
