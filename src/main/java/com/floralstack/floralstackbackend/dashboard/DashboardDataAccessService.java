package com.floralstack.floralstackbackend.dashboard;

import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardDataAccessService implements DashboardDataAccessServiceProvider {
    private final JdbcTemplateHelper jdbcTemplateHelper;

    @Autowired
    public DashboardDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
    }

    @Override
    public Overview getOverview() {
        String query = "" +
                "SELECT (" +
                "SELECT COUNT(*) " +
                "FROM plant ) " +
                "AS plants_count, " +
                "(SELECT COUNT(*) " +
                "FROM \"USER\" ) " +
                "AS users_count, " +
                "(SELECT COUNT(*) " +
                "FROM environment ) " +
                "AS environments_count, " +
                "(SELECT COUNT(*) " +
                "FROM static_sensor ) " +
                "AS static_sensors_count, " +
                "(SELECT COUNT(*) " +
                "FROM calibrated_sensor ) " +
                "AS calibrated_sensors_count, " +
                "(SELECT COUNT(*) " +
                "FROM actuator ) " +
                "AS actuators_count " +
                "FROM dual";
        return jdbcTemplateHelper.queryForObject(query, mapOverview());
    }

    // MAPPERS
    private RowMapper<Overview> mapOverview() {
        RowMapper<Overview> rowMapper = (resultSet, i) -> {
            return new Overview(resultSet.getInt("plants_count"),
                    resultSet.getInt("users_count"),
                    resultSet.getInt("environments_count"),
                    resultSet.getInt("static_sensors_count"),
                    resultSet.getInt("calibrated_sensors_count"),
                    resultSet.getInt("actuators_count"));
        };
        return rowMapper;
    }
}
