package com.example.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot.entities.Planet;

public interface PlanetDao extends JpaRepository<Planet,Integer>{
    Optional<Planet> findByName(String name); 

    @Transactional
    @Modifying
    @Query(value = "insert into planets values (default, :name , :ownerid)", nativeQuery = true)
    void createPlanet (@Param("name") String name, @Param("ownerid") int ownerid);

    @Transactional
    @Modifying
    @Query(value = "delete from planets where id = ?", nativeQuery = true)
    void deletePlanetById(@Param("id") int id);
}
