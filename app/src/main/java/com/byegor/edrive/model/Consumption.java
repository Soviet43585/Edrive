package com.byegor.edrive.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "consumption")
public class Consumption {


    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField(columnName = "fuel_consumption")
    private double fuelConsumption;
    @DatabaseField(columnName = "conventional_unit")
    private double conventionalUnits;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Machine machine;


    public Consumption() {}

    public Consumption(String name, double fuelConsumption, double conventionalUnits) {
        this.name = name;
        this.fuelConsumption = fuelConsumption;
        this.conventionalUnits = conventionalUnits;
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

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getConventionalUnits() {
        return conventionalUnits;
    }

    public void setConventionalUnits(double conventionalUnits) {
        this.conventionalUnits = conventionalUnits;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", conventionalUnits=" + conventionalUnits +
                ", machine=" + machine +
                '}';
    }



}
