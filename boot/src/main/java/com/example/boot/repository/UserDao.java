package com.example.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
    Optional<User> getUserByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "insert into users values (default, :username , :password)", nativeQuery = true)
    void createUser(@Param("username") String username, @Param("password") String password);  
}
