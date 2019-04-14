package com.example.api.dao;

import com.example.api.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParmRepository extends JpaRepository<Parameter,Integer> {

    Parameter findByParmId(Integer parmId);

}
