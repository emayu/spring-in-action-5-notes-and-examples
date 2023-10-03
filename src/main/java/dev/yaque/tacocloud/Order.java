/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yaque.tacocloud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

/**
 *
 * @author yaque
 */
@Entity
@Table(name="Taco_Order")
public class Order {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @NotBlank(message = "Name is required")
    private String deliveryName;
    
    @NotBlank(message = "Street is required")
    private String street;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    @Size(max = 2, message = "Use short state name(2 letter)")
    private String state;
    
    @NotBlank(message = "Zip code is required")
    private String zip;
    
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$"
            , message = "Must be formated MM/YY")
    private String ccExpiration;
    
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    
    private Date placedAt;
    
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();
    
    @PrePersist
    void prePersist(){
        this.setPlacedAt(new Date());
    }
    
    public void addDesign(Taco taco){
        tacos.add(taco);
    }

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
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

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
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
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", name=" + deliveryName + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", ccNumber=" + getLast4ccNumber() + ", ccExpiration=" + ccExpiration + ", ccCVV=" + ccCVV + ", placedAt=" + placedAt + '}';
    }
 
}
