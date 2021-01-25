package com.floralstack.floralstackbackend.actuator;


import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.sensor.Sensor;
import com.floralstack.floralstackbackend.sensor.StaticSensor;
import com.floralstack.floralstackbackend.user.User;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public class ActuatorDataAccessService implements ActuatorDataAccessServiceProvider {
    private final JdbcTemplateHelper jdbcTemplateHelper;

    @Autowired
    public ActuatorDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
    }

    @Override
    public Integer createActuator(@Valid Actuator actuator) {
        String query = "" +
                "INSERT INTO actuator ( " +
                "name, " +
                "description, " +
                "priority, " +
                "input_identifier) " +
                "VALUES(?, ?, ?, ?)";

        return jdbcTemplateHelper.update(query,
                actuator.getName(),
                actuator.getDescription(),
                actuator.getPriority(),
                actuator.getInputIdentifier());
    }

    @Override
    public Actuator getActuator(Integer id) {
        String query = "" +
                "SELECT * FROM actuator " +
                "WHERE actuator.id = ?";

         return jdbcTemplateHelper.queryForObject(query, mapActuator(), id);
    }

    @Override
    public List<Actuator> getAllActuators() {
        String query = "" +
                "SELECT * FROM actuator";
        return jdbcTemplateHelper.query(query, mapActuator());
    }

    @Override
    public Integer updateActuator(Actuator actuator) {
        String query = "" +
                "UPDATE actuator " +
                "SET actuator.name = ?, " +
                "actuator.description = ?, " +
                "actuator.priority = ?, " +
                "actuator.input_identifier = ? " +
                "WHERE actuator.id = ?";

        return jdbcTemplateHelper.update(query,
                actuator.getName(),
                actuator.getDescription(),
                actuator.getPriority(),
                actuator.getInputIdentifier(),
                actuator.getId());
    }

    @Override
    public Integer deleteActuator(Integer id) {
        String query = "" +
                "DELETE FROM actuator " +
                "WHERE actuator.id = ?";
        return jdbcTemplateHelper.update(query, id);
    }

    // MAPPERS
    private RowMapper<Actuator> mapActuator() {
        RowMapper<Actuator> rowMapper = (resultSet, i) -> {
            String name = resultSet.getString("description");
            name = resultSet.wasNull() ? null : name;

            String priority = resultSet.getString("priority");
            priority = resultSet.wasNull() ? null : priority;

            String input_identifier = resultSet.getString("input_identifier");
            input_identifier = resultSet.wasNull() ? null : input_identifier;

            Actuator actuator =  new Actuator(
                    resultSet.getInt("id"),
                    name,
                    resultSet.getString("description"),
                    priority,
                    input_identifier
            );
            return actuator;
        };
        return rowMapper;
    }
}
