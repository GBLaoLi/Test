package com.example.api.bean;

import com.example.api.entity.Address;
import com.example.api.entity.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiBean {

    private Address address;
    private List<Parameter> parameters;

}
