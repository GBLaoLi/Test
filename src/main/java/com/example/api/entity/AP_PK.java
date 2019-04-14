package com.example.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AP_PK implements Serializable {

    private static final long serialVersionUID = -2470510857424220408L;

    private Integer apiId;
    private Integer parmId;

}
