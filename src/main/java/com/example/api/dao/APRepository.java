package com.example.api.dao;

import com.example.api.entity.Addr_Parm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APRepository extends JpaRepository<Addr_Parm,Integer> {

    @Query(nativeQuery = true,value = "select * from addr_parm where api_id=?1")
    List<Addr_Parm> findAllParmId(Integer apiId);

}
