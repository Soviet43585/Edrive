package com.byegor.edrive.model;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;


@DatabaseTable(tableName = "machine")
public class Machine implements Serializable {


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private double fuel;


    public Machine() {}

    public Machine(String name, int fuel) {
        this.name = name;
        this.fuel = fuel;
    }

    public Machine(int id, String name, int fuel) {
        this.id = id;
        this.name = name;
        this.fuel = fuel;
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

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = round(fuel, 2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String toString() {
        return "Machine{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", fuel=" + fuel +
                    '}';

    }

    private double round(double value, double decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.ceil(value*scale) / scale;
    }
}
