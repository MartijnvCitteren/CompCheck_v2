package com.martijn.CompCheckV2.security;

import com.martijn.CompCheckV2.presistence.entity.UserRoles;
import com.martijn.CompCheckV2.rest.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsModel implements UserDetails {
    private final String email;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public UserDetailsModel(UserDto user) {
        this.email = user.email();
        this.password = user.password();
        if(user.role() == null){
            this.authorities.add(new SimpleGrantedAuthority(UserRoles.ROLE_USER.name()));
        }
        else {
            this.authorities.add(new SimpleGrantedAuthority(user.role().name()));
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
