package com.example.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.entities.Moon;
import com.example.boot.exceptions.EntityNotFound;
import com.example.boot.repository.MoonDao;

@Service
public class MoonService {
    @Autowired
    private MoonDao moonDao;

    public Moon findMoonById(int id){
        Optional<Moon> possibleMoon = this.moonDao.findById(id);
        if(possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new EntityNotFound("Moon not found");
        }
    }

    public Moon findByName(String name){
        Optional<Moon> possibleMoon = this.moonDao.findByName(name);
        if(possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new EntityNotFound("Moon not found");
        }
    }

    public List<Moon> findAllMoons(){
        List<Moon> moons = this.moonDao.findAll();
        if(moons.size() != 0){
            return moons;
        } else {
            throw new EntityNotFound("No moons found in the database");
        }
    }

    public String createMoon(Moon moon){
        this.moonDao.createMoon(moon.getName(), moon.getMyPlanetId());
        return "moon created";
    }

    public String deleteMoonbyId(int id){
        this.moonDao.deleteById(id);
        return "Player with given id deleted";
    } 
}
