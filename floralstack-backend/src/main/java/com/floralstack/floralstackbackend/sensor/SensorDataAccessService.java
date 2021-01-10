package com.floralstack.floralstackbackend.sensor;

import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.user.User;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class SensorDataAccessService implements SensorDataAccessServiceProvider{
    private final JdbcTemplateHelper jdbcTemplateHelper;

    @Autowired
    public SensorDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
    }

    public static class CreateSensorResult {
        private Integer queryResult;
        private Integer createdSensorId;

        public CreateSensorResult(Integer queryResult, Integer createdSensorId) {
            this.queryResult = queryResult;
            this.createdSensorId = createdSensorId;
        }

        public Integer getQueryResult() {
            return queryResult;
        }

        public Integer getCreatedSensorId() {
            return createdSensorId;
        }
    }

    @Override
    public CreateSensorResult createSensor(Sensor sensor) {
        String query = "" +
                "INSERT INTO sensor " +
                "(name, " +
                "description, " +
                "priority, " +
                "output_identifier, " +
                "unit_of_measurement, " +
                "last_measurement_value, " +
                "threshold_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        Integer insertionResult = jdbcTemplateHelper.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement statement = connection.prepareStatement(query, new String[] { "id" });
                statement.setString(1, sensor.getName());
                statement.setString(2, sensor.getDescription());
                statement.setString(3, sensor.getPriority());
                statement.setString(4, sensor.getOutputIdentifier());
                statement.setString(5, sensor.getUnitOfMeasurement());
                statement.setDouble(6, sensor.getLastMeasurementValue());
                statement.setString(7, sensor.getThresholdType());
                return statement;
            }
        }, generatedKeyHolder);
        Integer sensorId = Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
        return new CreateSensorResult(insertionResult, sensorId);
    }

    @Override
    public Integer createStaticSensor(StaticSensor staticSensor) {
        String query = "" +
                "INSERT INTO static_sensor " +
                "(id, " +
                "threshold_offset) " +
                "VALUES (?, ?)";

        return jdbcTemplateHelper.update(query,
                staticSensor.getId(),
                staticSensor.getThresholdOffset());
    }

    @Override
    public List<StaticSensor> getAllStaticSensors() {
        String query = "" +
                "SELECT sensor.id," +
                "name, " +
                "description, " +
                "priority, " +
                "output_identifier, " +
                "unit_of_measurement, " +
                "last_measurement_value, " +
                "threshold_type, " +
                "static_sensor.threshold_offset " +
                "FROM sensor INNER JOIN static_sensor " +
                "ON sensor.id = static_sensor.id";
        return jdbcTemplateHelper.query(query, staticSensorRowMapper());
    }

    @Override
    public StaticSensor getStaticSensor(Integer id) {
        String query = "" +
                "SELECT sensor.id," +
                "name, " +
                "description, " +
                "priority, " +
                "output_identifier, " +
                "unit_of_measurement, " +
                "last_measurement_value, " +
                "threshold_type, " +
                "static_sensor.threshold_offset " +
                "FROM sensor INNER JOIN static_sensor " +
                "ON sensor.id = static_sensor.id " +
                "WHERE sensor.id = ?";

        return jdbcTemplateHelper.queryForObject(query, staticSensorRowMapper(), id);
    }

    @Override
    public void updateStaticSensor(StaticSensor sensor) {
        String query = "" +
                "UPDATE static_sensor ";
    }
    // MAPPERS

    private RowMapper<StaticSensor> staticSensorRowMapper() {
        RowMapper<StaticSensor> rowMapper = (resultSet, i) -> {

            Double lastMeasurementValue = resultSet.getDouble("last_measurement_value");
            lastMeasurementValue = resultSet.wasNull() ? null : lastMeasurementValue;

            Double thresholdOffset = resultSet.getDouble("threshold_offset");
            thresholdOffset = resultSet.wasNull() ? null : thresholdOffset;

            StaticSensor sensor =  new StaticSensor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("priority"),
                    resultSet.getString("output_identifier"),
                    resultSet.getString("unit_of_measurement"),
                    lastMeasurementValue,
                    resultSet.getString("threshold_type"),
                    thresholdOffset);
            return sensor;
        };
        return rowMapper;
    }
}
