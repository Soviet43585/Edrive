package com.byegor.edrive.model;

public class Consumption {

    private int id;
    private String name;
    private float fuelConsumption;
    private float conventionalUnits;
    private int machineId;
    private static int counter = 1;

    public Consumption(String name, float fuelConsumption, float conventionalUnits, int machineId) {
        this.id = counter++;
        this.name = name;
        this.fuelConsumption = fuelConsumption;
        this.conventionalUnits = conventionalUnits;
        this.machineId = machineId;
    }

    public Consumption(int id, String name, float fuelConsumption, float conventionalUnits, int machineId) {
        this.id = id;
        this.name = name;
        this.fuelConsumption = fuelConsumption;
        this.conventionalUnits = conventionalUnits;
        this.machineId = machineId;
    }

    public Consumption() {

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

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public float getConventionalUnits() {
        return conventionalUnits;
    }

    public void setConventionalUnits(float conventionalUnits) {
        this.conventionalUnits = conventionalUnits;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", conventionalUnits=" + conventionalUnits +
                ", machineId=" + machineId +
                '}';
    }
}
