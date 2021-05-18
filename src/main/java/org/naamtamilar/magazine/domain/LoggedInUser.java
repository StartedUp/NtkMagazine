package org.naamtamilar.magazine.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class LoggedInUser extends User implements UserDetails {
    private  String password;
    private  String username;
    /*private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;*/
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;

    public LoggedInUser(User user) {
        super(user);
        this.password=user.getPassword();
        this.username=user.getEmail();
        this.isEnabled=user.isActive();
        this.authorities=getAuthorities();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        authorities =
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + getRole().getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.isEnabled;
    }
}