package com.example.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(AP_PK.class)
@Table(name = "addr_parm")
@Data
public class Addr_Parm {

    @Id
    private Integer apiId;
    @Id
    private Integer parmId;

    public Addr_Parm(){ }

    public Addr_Parm(Integer api_id,Integer parm_id){
        this.apiId = api_id;
        this.parmId = parm_id;
    }

}
