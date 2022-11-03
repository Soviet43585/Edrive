package com.byegor.edrive.dao;

import com.byegor.edrive.model.Consumption;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class ConsumptionDAO  extends BaseDaoImpl<Consumption, Integer> {

    public ConsumptionDAO(ConnectionSource connectionSource, Class<Consumption> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Consumption> getAllConsumptions() throws SQLException{
        return this.queryForAll();
    }

    public List<Consumption> getConsumptionByMachineId(int id) throws SQLException {
        QueryBuilder<Consumption, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq("machine_id", id);
        PreparedQuery<Consumption> preparedQuery = queryBuilder.prepare();
        List<Consumption> consumptions = query(preparedQuery);
        return consumptions;
    }

    public void deleteConsumptions(List<Consumption> consumptions) throws SQLException {
        this.delete(consumptions);
    }

    @Override
    public String toString() {
        return "ConsumptionDAO{" +
                "dataClass=" + dataClass.toString() +
                '}';
    }
}
