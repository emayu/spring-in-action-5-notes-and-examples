/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author yaque
 */
@Entity(name = "users")
public class User implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private final String username;
    private final String fullname;
    private final String street;
    private final String state;
    private final String city;
    private final String zip;
    private final String phoneNumber;
    private final String password;

    public User() {
        this(null, null, null, null, null, null, null, null);
    }

    public User(String username, String fullname, String street, String state, String city, String zip, String phoneNumber, String password) {
        this.username = username;
        this.fullname = fullname;
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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
