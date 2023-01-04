package com.example.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.entities.Planet;
import com.example.boot.exceptions.EntityNotFound;
import com.example.boot.repository.PlanetDao;

@Service
public class PlanetService {
    @Autowired
    private PlanetDao planetDao;
    public Planet findPlanetById(int id) {
        Optional<Planet> possiblePlanet = this.planetDao.findById(id);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("Planet not found");
        }

    }
    
    public Planet findPlanetByName(String name) {
        Optional<Planet> possiblePlanet = this.planetDao.findByName(name);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("Planet not found");
        }

    }

    public List<Planet> findAllPlanets() {
        List<Planet> planets = this.planetDao.findAll();
        if(planets.size() != 0){
            return planets;
        } else {
            throw new EntityNotFound("There are no planets in the database");
        }
    }
    
    public String createPlanet(Planet planet) {
        this.planetDao.createPlanet(planet.getName(), planet.getOwnerid());
        return "Planet created";
    }

    public String deletePlanetbyId(int id) {
        this.planetDao.deletePlanetById(id);
        return "Planet was deleted";
    }
}
