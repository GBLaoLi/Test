package com.example.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "api_address")
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = -2470510857424220410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer apiId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer userId;

    public Address(){}

    public Address(String address, Integer user_id) {
        this.address = address;
        this.userId = user_id;
    }
}
