/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud;

import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author yaque
 */
public class Order {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Street is required")
    private String street;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    private String state;
    
    @NotBlank(message = "Zip code is required")
    private String zip;
    
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$"
            , message = "Must be formated MM/YY")
    private String ccExpiration;
    
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCCV;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }
    
    public String getLast4ccNumber() {
        if(ccNumber != null){
            if(ccNumber.length() >= 4){
                return "**** - " + ccNumber.substring(ccNumber.length() - 4 , ccNumber.length());
            }
            return ccNumber;
        }
        return "";
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCCV() {
        return ccCCV;
    }

    public void setCcCCV(String ccCCV) {
        this.ccCCV = ccCCV;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Order{" + "name=" + name + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", ccNumber=" + getLast4ccNumber()  + ", ccExpiration=" + ccExpiration + ", ccCVV=" + ccCCV + '}';
    }
    
    
    
}
