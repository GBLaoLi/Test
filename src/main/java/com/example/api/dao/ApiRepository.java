package com.example.api.dao;

import com.example.api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<Address,Integer> {

    List<Address> findAllByUserId(Integer userId);

    Address findByApiId(Integer apiId);

    Address findByAddress(String address);

}
