package com.falkordb.springai.app;

import org.springframework.ai.vectorstore.auth.MultiTenantAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserOfCustomer implements UserDetails, MultiTenantAuthentication {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private String customer;

    public UserOfCustomer(String username, String password, Collection<? extends GrantedAuthority> authorities, String customer) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.customer = customer;
    }


    @Override
    public String getTenantId() {
        return customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

}
