package com.example.boot.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entities.Moon;
import com.example.boot.exceptions.AuthenticationFailed;
import com.example.boot.exceptions.EntityNotFound;
import com.example.boot.service.MoonService;

@RestController
public class MoonController {
    private static Logger moonLogger = LoggerFactory.getLogger(MoonController.class);
    
    @Autowired 
    private MoonService moonService;
    
    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFailed(AuthenticationFailed e){
        moonLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
   @ExceptionHandler(EntityNotFound.class)
   public ResponseEntity<String> entityNotFound(EntityNotFound e){
        moonLogger.error(e.getLocalizedMessage(), e);
       return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(PSQLException.class)
   public ResponseEntity<String> sqlIssue(PSQLException e){
        moonLogger.error(e.getLocalizedMessage(), e);
       return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
   }

   @ExceptionHandler(EmptyResultDataAccessException.class)
   public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        moonLogger.error(e.getLocalizedMessage(), e);
       return new ResponseEntity<>("could not delete moon", HttpStatus.BAD_REQUEST);
   }

    @GetMapping("/api/moon/id/{id}")
    public ResponseEntity<Moon> findById(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.findMoonById(id),HttpStatus.OK);
    }

    @GetMapping("/api/moon/{name}")
    public ResponseEntity<Moon> findByName(@PathVariable String name){
        return new ResponseEntity<>(this.moonService.findByName(name),HttpStatus.OK);
    }

    @GetMapping("/api/moon")
    public ResponseEntity<List<Moon>> findAll(){
        return new ResponseEntity<>(this.moonService.findAllMoons(), HttpStatus.OK);
    }

    @PostMapping("/api/moon")
    public ResponseEntity<String> createMoon(@RequestBody Moon moon){
        String message = this.moonService.createMoon(moon);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/moon/{id}")
    public ResponseEntity<String> deleteMoon(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.deleteMoonbyId(id), HttpStatus.OK);
    }
    
}
