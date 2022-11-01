package com.byegor.edrive.dao;


import com.byegor.edrive.model.Machine;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class MachineDAO extends BaseDaoImpl<Machine, Integer> {


    public MachineDAO(ConnectionSource connectionSource, Class<Machine> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Machine> getAllMachines() throws SQLException {
        return this.queryForAll();
    }

    public void updateMachine(Machine machine) throws SQLException {
        this.update(machine);
    }

    @Override
    public String toString() {
        return "MachineDAO{" +
                "dataClass=" + dataClass.toString() +
                '}';
    }

    //    public List<Consumption> getConsumptionFromMachine() {
//        return this.q
//    }
}
