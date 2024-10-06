package com.falkordb.springai.app;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from your data source (e.g., database)
        // Here, we are just creating a dummy user for demonstration.

        if ("user1".equals(username)) {
            return new UserOfCustomer("user1", "{noop}password", List.of(() -> "USER"), "customer1");
        } else if ("user2".equals(username)) {
            return new UserOfCustomer("user2", "{noop}password", List.of(() -> "USER"), "customer2");
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
