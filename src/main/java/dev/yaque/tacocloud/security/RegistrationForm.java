/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud.security;

import dev.yaque.tacocloud.User;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author yaque
 */
public class RegistrationForm {
    
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    
    public User toUser(PasswordEncoder encoder){
        return new User(username, fullname, street, state, city, zip, phone, encoder.encode(password));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    

    @Override
    public String toString() {
        return "RegistrationForm{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phone=" + phone + '}';
    }
    
    
    
}
