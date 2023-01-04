package com.example.boot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column 
    private int ownerid;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOwnerid() {
        return ownerid;
    }
    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }
    @Override
    public String toString() {
        return "Planet [id=" + id + ", name=" + name + ", ownerid=" + ownerid + "]";
    }

    
    
}
