package org.sotobotero.customer.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String company;
    private String position;
    private String website;
    private String twitter;
    private String facebook;
    private String linkedin;
    private String github;
    private String instagram;
    private String youtube;
    private String tiktok;
    private String snapchat;
    private String twitch;
    private String other;
    private String notes;
    private int age;    

}
  
