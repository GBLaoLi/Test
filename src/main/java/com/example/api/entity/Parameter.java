package com.example.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "api_parm")
@Data
public class Parameter implements Serializable {

    private static final long serialVersionUID = -2470510857424220420L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parmId;

    @Column(nullable = false)
    private String parmName;

    @Column(nullable = false)
    private String parmType;

    @Column(nullable = false)
    private Integer parmIsmust;

    @Column(columnDefinition = "text")
    private String parmDescribe;

    public Parameter(){}

    public Parameter(String parmName, String parmType, Integer parmIsmust, String parmDescribe) {
        this.parmName = parmName;
        this.parmType = parmType;
        this.parmIsmust = parmIsmust;
        this.parmDescribe = parmDescribe;
    }
}
