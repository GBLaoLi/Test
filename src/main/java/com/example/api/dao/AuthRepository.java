package com.example.api.dao;

import com.example.api.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Authority,Integer> {

    @Query(nativeQuery = true,value = "select * from authority where id=?1")
    Authority findByAuthId(Integer id);

}
