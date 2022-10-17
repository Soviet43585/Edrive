package com.byegor.edrive.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Machine {

    private int id;
    private String name;
    private int fuel;
    private static int counter = 1;


    public Machine(String name, int fuel) {
        this.id = counter++;
        this.name = name;
        this.fuel = fuel;
    }

    public Machine(int id, String name, int fuel) {
        this.id = id;
        this.name = name;
        this.fuel = fuel;
    }

    public Machine() {
    }

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

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fuel=" + fuel +
                '}';
    }

}
