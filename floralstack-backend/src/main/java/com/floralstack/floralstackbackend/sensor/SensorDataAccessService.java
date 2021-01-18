package com.floralstack.floralstackbackend.sensor;

import com.floralstack.floralstackbackend.actuator.Actuator;
import com.floralstack.floralstackbackend.sensor.CreateSensorResult;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class SensorDataAccessService implements SensorDataAccessServiceProvider{
    private final JdbcTemplateHelper jdbcTemplateHelper;

    @Autowired
    public SensorDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
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
                PreparedStatement statement = connection.prepareStatement(query, new String[]{"id"});
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
                "sensor.name, " +
                "sensor.description, " +
                "sensor.priority, " +
                "output_identifier, " +
                "unit_of_measurement, " +
                "last_measurement_value, " +
                "threshold_type, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier, " +
                "static_sensor.threshold_offset " +
                "FROM sensor INNER JOIN static_sensor " +
                "ON sensor.id = static_sensor.id " +
                "LEFT JOIN actuator a ON " +
                "actuator_id = a.id";
        return jdbcTemplateHelper.query(query, staticSensorRowMapper());
    }

    @Override
    public List<StaticSensor> getAllUnattachedStaticSensors() {
        String query = "" +
                "SELECT sensor.id, " +
                "sensor.name, " +
                "sensor.description, " +
                "sensor.priority, " +
                "sensor.output_identifier, " +
                "sensor.unit_of_measurement, " +
                "sensor.last_measurement_value, " +
                "sensor.threshold_type, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier, " +
                "static_sensor.threshold_offset " +
                "FROM sensor INNER JOIN static_sensor " +
                "ON sensor.id = static_sensor.id " +
                "LEFT JOIN actuator a ON " +
                "actuator_id = a.id " +
                "WHERE sensor.id NOT IN " +
                "(SELECT static_sensor_id FROM plant_static_sensor)";
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
                "actuator_id, " +
                "static_sensor.threshold_offset " +
                "FROM sensor INNER JOIN static_sensor " +
                "ON sensor.id = static_sensor.id " +
                "WHERE sensor.id = ?";

        return jdbcTemplateHelper.queryForObject(query, staticSensorRowMapper(), id);
    }

    @Override
    public Integer updateSensor(Sensor sensor) {
        String sensorUpdateQuery = "" +
                "UPDATE sensor " +
                "SET name = ?, " +
                "description = ?, " +
                "priority = ?, " +
                "output_identifier = ?, " +
                "unit_of_measurement = ?, " +
                "last_measurement_value = ?, "+
                "threshold_type = ?, " +
                "WHERE id = ?";

        Integer sensorUpdate = jdbcTemplateHelper.update(
                sensorUpdateQuery,
                sensor.getName(),
                sensor.getDescription(),
                sensor.getPriority(),
                sensor.getOutputIdentifier(),
                sensor.getUnitOfMeasurement(),
                sensor.getLastMeasurementValue(),
                sensor.getThresholdType(),
                sensor.getId());
        if(sensorUpdate == 0) {
            return sensorUpdate;
        }

        String staticSensorUpdateQuery = "" +
                "UPDATE static_sensor " +
                "SET threshold_offset = ? " +
                "WHERE id = ?";

        String calibratedSensorUpdateQuery = "" +
                "UPDATE calibrated_sensor " +
                "SET max_value = ?, " +
                "min_value = ?, " +
                "percentage_threshold = ? " +
                "WHERE id = ?";

        if(sensor.getClass().equals(StaticSensor.class))
        {
            return jdbcTemplateHelper.update(
                    staticSensorUpdateQuery,
                    ((StaticSensor) sensor).getThresholdOffset(),
                    sensor.getId());
        }
        else if(sensor.getClass().equals(CalibratedSensor.class))
        {
            return jdbcTemplateHelper.update(
                    calibratedSensorUpdateQuery,
                    ((CalibratedSensor) sensor).getMaxValue(),
                    ((CalibratedSensor) sensor).getMinValue(),
                    ((CalibratedSensor) sensor).getPercentageThreshold(),
                    sensor.getId());
        }
        return 0;
    }

    @Override
    public Integer deleteSensor(Integer id) {
        String query = "" +
                "DELETE FROM sensor " +
                "WHERE id = ?";

        return jdbcTemplateHelper.update(query, id);
    }

    @Override
    public Integer createCalibratedSensor(CalibratedSensor calibratedSensor) {
        String query = "" +
                "INSERT INTO calibrated_sensor " +
                "(id, " +
                "max_value, " +
                "min_value," +
                "percentage_threshold) " +
                "VALUES (?, ?, ?, ?)";

        return jdbcTemplateHelper.update(query,
                calibratedSensor.getId(),
                calibratedSensor.getMaxValue(),
                calibratedSensor.getMinValue(),
                calibratedSensor.getPercentageThreshold());
    }

    @Override
    public List<CalibratedSensor> getAllCalibratedSensors() {
        String query = "" +
                "SELECT sensor.id, " +
                "sensor.name, " +
                "sensor.description, " +
                "sensor.priority, " +
                "sensor.output_identifier, " +
                "sensor.unit_of_measurement, " +
                "sensor.last_measurement_value, " +
                "sensor.threshold_type, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier, " +
                "calibrated_sensor.max_value, " +
                "calibrated_sensor.min_value, " +
                "calibrated_sensor.percentage_threshold " +
                "FROM sensor INNER JOIN calibrated_sensor " +
                "ON sensor.id = calibrated_sensor.id " +
                "LEFT JOIN actuator a ON " +
                "actuator_id = a.id ";
        return jdbcTemplateHelper.query(query, calibratedSensorRowMapper());
    }

    @Override
    public List<CalibratedSensor> getAllUnattachedCalibratedSensors() {
        String query = "" +
                "SELECT sensor.id, " +
                "sensor.name, " +
                "sensor.description, " +
                "sensor.priority, " +
                "sensor.output_identifier, " +
                "sensor.unit_of_measurement, " +
                "sensor.last_measurement_value, " +
                "sensor.threshold_type, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier, " +
                "calibrated_sensor.max_value, " +
                "calibrated_sensor.min_value, " +
                "calibrated_sensor.percentage_threshold " +
                "FROM sensor INNER JOIN calibrated_sensor " +
                "ON sensor.id = calibrated_sensor.id " +
                "LEFT JOIN actuator a ON " +
                "actuator_id = a.id " +
                "WHERE sensor.id NOT IN " +
                "(SELECT calibrated_sensor_id FROM plant_calibrated_sensor)";
        return jdbcTemplateHelper.query(query, calibratedSensorRowMapper());
    }

    @Override
    public CalibratedSensor getCalibratedSensor(Integer id) {
        String query = "" +
                "SELECT sensor.id," +
                "sensor.name, " +
                "sensor.description, " +
                "sensor.priority, " +
                "sensor.output_identifier, " +
                "sensor.unit_of_measurement, " +
                "sensor.last_measurement_value, " +
                "sensor.threshold_type," +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier, " +
                "calibrated_sensor.max_value, " +
                "calibrated_sensor.min_value," +
                "calibrated_sensor.threshold_type " +
                "FROM sensor INNER JOIN calibrated_sensor " +
                "LEFT JOIN actuator a ON " +
                "actuator_id = a.id " +
                "WHERE sensor.id = ?";

        return jdbcTemplateHelper.queryForObject(query, calibratedSensorRowMapper(), id);
    }

    @Override
    public void attachActuator(Integer id, Integer id1) {
        String actuatorAttachQuery = "" +
                "UPDATE sensor " +
                "SET actuator_id = ? " +
                "WHERE id = ?";
        jdbcTemplateHelper.update(actuatorAttachQuery, id, id1);
    }

    @Override
    public void detachActuator(Integer id, Integer id1) {
        String actuatorAttachQuery = "" +
                "UPDATE sensor " +
                "SET actuator_id = NULL " +
                "WHERE sensor.id = ?";
        jdbcTemplateHelper.update(actuatorAttachQuery, id, id1);
    }

    // MAPPERS
    private RowMapper<StaticSensor> staticSensorRowMapper() {
        RowMapper<StaticSensor> rowMapper = (resultSet, i) -> {

            Double lastMeasurementValue = resultSet.getDouble("last_measurement_value");
            lastMeasurementValue = resultSet.wasNull() ? null : lastMeasurementValue;

            Double thresholdOffset = resultSet.getDouble("threshold_offset");
            thresholdOffset = resultSet.wasNull() ? null : thresholdOffset;

            Integer actuatorId = resultSet.getInt("actuator_id");
            actuatorId = resultSet.wasNull() ? null : actuatorId;
            Actuator actuator = null;
            if(actuatorId != null) {
                actuator = new Actuator(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("priority"),
                        resultSet.getString("input_identifier"));
            }


            StaticSensor sensor =  new StaticSensor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("priority"),
                    resultSet.getString("output_identifier"),
                    resultSet.getString("unit_of_measurement"),
                    lastMeasurementValue,
                    resultSet.getString("threshold_type"),
                    actuator,
                    thresholdOffset);
            return sensor;
        };
        return rowMapper;
    }

    private RowMapper<CalibratedSensor> calibratedSensorRowMapper() {
        RowMapper<CalibratedSensor> rowMapper = (resultSet, i) -> {

            Double lastMeasurementValue = resultSet.getDouble("last_measurement_value");
            lastMeasurementValue = resultSet.wasNull() ? null : lastMeasurementValue;

            Double maxValue = resultSet.getDouble("max_value");
            maxValue = resultSet.wasNull() ? null : maxValue;

            Double minValue = resultSet.getDouble("min_value");
            minValue = resultSet.wasNull() ? null : minValue;

            Double percentageThreshold = resultSet.getDouble("percentage_threshold");
            percentageThreshold = resultSet.wasNull() ? null : percentageThreshold;

            Integer actuatorId = resultSet.getInt("actuator_id");
            actuatorId = resultSet.wasNull() ? null : actuatorId;
            Actuator actuator = null;
            if(actuatorId != null) {
                actuator = new Actuator(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("priority"),
                        resultSet.getString("input_identifier"));
            }

            CalibratedSensor sensor =  new CalibratedSensor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("priority"),
                    resultSet.getString("output_identifier"),
                    resultSet.getString("unit_of_measurement"),
                    lastMeasurementValue,
                    resultSet.getString("threshold_type"),
                    actuator,
                    maxValue,
                    minValue,
                    percentageThreshold);
            return sensor;
        };
        return rowMapper;
    }
}
