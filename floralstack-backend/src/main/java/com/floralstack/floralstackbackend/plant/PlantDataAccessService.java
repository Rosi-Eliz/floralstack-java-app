package com.floralstack.floralstackbackend.plant;

import com.floralstack.floralstackbackend.actuator.Actuator;
import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.exception.ApiRequestException;
import com.floralstack.floralstackbackend.sensor.CalibratedSensor;
import com.floralstack.floralstackbackend.sensor.StaticSensor;
import com.floralstack.floralstackbackend.user.User;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.*;

@Repository
public class PlantDataAccessService implements PlantDataAccessServiceProvider{
    private final JdbcTemplateHelper jdbcTemplateHelper;
    @Autowired
    public PlantDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
    }

    @Override
    public int createPlant(@Valid Plant plant, Date creationDate) {

        String query = "" +
                "INSERT INTO plant (" +
                " name, " +
                " description, " +
                " creation_date) " +
                "VALUES (?, ?, ?)";

        return jdbcTemplateHelper.update(
                query,
                plant.getName(),
                plant.getDescription(),
                creationDate
        );
    }

    @Override
    public Plant getPlant(Integer id) {
        String query = "" +
                "SELECT " +
                "p.id AS plant_id, " +
                "p.name AS plant_name, " +
                "p.description AS plant_description, " +
                "p.owner_id AS owner_id, " +
                "p.creation_date AS plant_creation_date, " +
                "u.id AS user_id, " +
                "u.first_name, " +
                "u.last_name, " +
                "u.birth_date, " +
                "u.user_role, " +
                "u.email, " +
                "e.id AS environment_id, " +
                "e.name AS environment_name, " +
                "e.description AS environment_description, " +
                "ssp.id AS static_sensor_id, " +
                "ssp.name AS static_name, " +
                "ssp.description AS static_description, " +
                "ssp.priority AS static_priority, " +
                "ssp.output_identifier AS static_output_identifier, " +
                "ssp.last_measurement_value AS static_last_measurement_value, " +
                "ssp.unit_of_measurement AS static_unit_of_measurement, " +
                "ssp.threshold_type AS static_threshold_type, " +
                "ss.threshold_offset AS threshold_offset, " +
                "csp.id AS calibrated_sensor_id, " +
                "csp.name AS calibrated_name, " +
                "csp.description AS calibrated_description, " +
                "csp.priority AS calibrated_priority, " +
                "csp.output_identifier AS calibrated_output_identifier, " +
                "csp.last_measurement_value AS calibrated_last_measurement_value, " +
                "csp.unit_of_measurement AS calibrated_unit_of_measurement, " +
                "csp.threshold_type AS calibrated_threshold_type, " +
                "cs.max_value AS max_value, " +
                "cs.min_value AS min_value, " +
                "cs.percentage_threshold AS percentage_threshold, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier " +
                "FROM plant p " +
                "LEFT JOIN " +
                "\"USER\" u ON " +
                "p.owner_id = u.id " +
                "LEFT JOIN " +
                "environment e ON " +
                "p.environment_id = e.id " +
                "" +
                "LEFT JOIN " +
                "plant_static_sensor pss ON " +
                "p.id = pss.plant_id " +
                "LEFT JOIN " +
                "static_sensor ss ON " +
                "pss.static_sensor_id = ss.id " +
                "LEFT JOIN " +
                "sensor ssp ON " +
                "ss.id = ssp.id " +
                "" +
                "LEFT JOIN " +
                "plant_calibrated_sensor pcs ON " +
                "p.id = pcs.plant_id " +
                "LEFT JOIN " +
                "calibrated_sensor cs ON " +
                "pcs.calibrated_sensor_id = cs.id " +
                "LEFT JOIN " +
                "sensor csp ON " +
                "cs.id = csp.id " +
                "LEFT JOIN " +
                "actuator a ON " +
                "csp.actuator_id = a.id " +
                "WHERE p.id = ?";

        List<Plant> result = jdbcTemplateHelper.query(query, plantResultSetExtractor(), id);
        if(result.size() > 1)
           throw new ApiRequestException("Failed request.");
        return result.get(0);
    }

    @Override
    public List<Plant> getPlantsForOwner(Integer id) {
        String query = "" +
                "SELECT " +
                "p.id AS plant_id, " +
                "p.name AS plant_name, " +
                "p.description AS plant_description, " +
                "p.owner_id AS owner_id, " +
                "p.creation_date AS plant_creation_date, " +
                "u.id AS user_id, " +
                "u.first_name, " +
                "u.last_name, " +
                "u.birth_date, " +
                "u.user_role, " +
                "u.email, " +
                "e.id AS environment_id, " +
                "e.name AS environment_name, " +
                "e.description AS environment_description, " +
                "ssp.id AS static_sensor_id, " +
                "ssp.name AS static_name, " +
                "ssp.description AS static_description, " +
                "ssp.priority AS static_priority, " +
                "ssp.output_identifier AS static_output_identifier, " +
                "ssp.last_measurement_value AS static_last_measurement_value, " +
                "ssp.unit_of_measurement AS static_unit_of_measurement, " +
                "ssp.threshold_type AS static_threshold_type, " +
                "ss.threshold_offset AS threshold_offset, " +
                "csp.id AS calibrated_sensor_id, " +
                "csp.name AS calibrated_name, " +
                "csp.description AS calibrated_description, " +
                "csp.priority AS calibrated_priority, " +
                "csp.output_identifier AS calibrated_output_identifier, " +
                "csp.last_measurement_value AS calibrated_last_measurement_value, " +
                "csp.unit_of_measurement AS calibrated_unit_of_measurement, " +
                "csp.threshold_type AS calibrated_threshold_type, " +
                "cs.max_value AS max_value, " +
                "cs.min_value AS min_value, " +
                "cs.percentage_threshold AS percentage_threshold, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier " +
                "FROM plant p " +
                "LEFT JOIN " +
                "\"USER\" u ON " +
                "p.owner_id = u.id " +
                "LEFT JOIN " +
                "environment e ON " +
                "p.environment_id = e.id " +
                "" +
                "LEFT JOIN " +
                "plant_static_sensor pss ON " +
                "p.id = pss.plant_id " +
                "LEFT JOIN " +
                "static_sensor ss ON " +
                "pss.static_sensor_id = ss.id " +
                "LEFT JOIN " +
                "sensor ssp ON " +
                "ss.id = ssp.id " +
                "" +
                "LEFT JOIN " +
                "plant_calibrated_sensor pcs ON " +
                "p.id = pcs.plant_id " +
                "LEFT JOIN " +
                "calibrated_sensor cs ON " +
                "pcs.calibrated_sensor_id = cs.id " +
                "LEFT JOIN " +
                "sensor csp ON " +
                "cs.id = csp.id " +
                "LEFT JOIN " +
                "actuator a ON " +
                "csp.actuator_id = a.id "  +
                "WHERE u.id = ?";

        return jdbcTemplateHelper.query(query, plantResultSetExtractor(), id);
    }

    @Override
    public List<Plant> getAllPlantsForEnvironment(Integer id) {
        String query = "" +
                "SELECT " +
                "p.id AS plant_id, " +
                "p.name AS plant_name, " +
                "p.description AS plant_description, " +
                "p.owner_id AS owner_id, " +
                "p.creation_date AS plant_creation_date, " +
                "u.id AS user_id, " +
                "u.first_name, " +
                "u.last_name, " +
                "u.birth_date, " +
                "u.user_role, " +
                "u.email, " +
                "e.id AS environment_id, " +
                "e.name AS environment_name, " +
                "e.description AS environment_description, " +
                "ssp.id AS static_sensor_id, " +
                "ssp.name AS static_name, " +
                "ssp.description AS static_description, " +
                "ssp.priority AS static_priority, " +
                "ssp.output_identifier AS static_output_identifier, " +
                "ssp.last_measurement_value AS static_last_measurement_value, " +
                "ssp.unit_of_measurement AS static_unit_of_measurement, " +
                "ssp.threshold_type AS static_threshold_type, " +
                "ss.threshold_offset AS threshold_offset, " +
                "csp.id AS calibrated_sensor_id, " +
                "csp.name AS calibrated_name, " +
                "csp.description AS calibrated_description, " +
                "csp.priority AS calibrated_priority, " +
                "csp.output_identifier AS calibrated_output_identifier, " +
                "csp.last_measurement_value AS calibrated_last_measurement_value, " +
                "csp.unit_of_measurement AS calibrated_unit_of_measurement, " +
                "csp.threshold_type AS calibrated_threshold_type, " +
                "cs.max_value AS max_value, " +
                "cs.min_value AS min_value, " +
                "cs.percentage_threshold AS percentage_threshold, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier " +
                "FROM plant p " +
                "LEFT JOIN " +
                "\"USER\" u ON " +
                "p.owner_id = u.id " +
                "LEFT JOIN " +
                "environment e ON " +
                "p.environment_id = e.id " +
                "" +
                "LEFT JOIN " +
                "plant_static_sensor pss ON " +
                "p.id = pss.plant_id " +
                "LEFT JOIN " +
                "static_sensor ss ON " +
                "pss.static_sensor_id = ss.id " +
                "LEFT JOIN " +
                "sensor ssp ON " +
                "ss.id = ssp.id " +
                "" +
                "LEFT JOIN " +
                "plant_calibrated_sensor pcs ON " +
                "p.id = pcs.plant_id " +
                "LEFT JOIN " +
                "calibrated_sensor cs ON " +
                "pcs.calibrated_sensor_id = cs.id " +
                "LEFT JOIN " +
                "sensor csp ON " +
                "cs.id = csp.id " +
                "LEFT JOIN " +
                "actuator a ON " +
                "csp.actuator_id = a.id "  +
                "WHERE environment_id = ?";

        return jdbcTemplateHelper.query(query, plantResultSetExtractor(), id);
    }

    @Override
    public List<Plant> getAllPlants() {
        String query = "" +
                "SELECT " +
                "p.id AS plant_id, " +
                "p.name AS plant_name, " +
                "p.description AS plant_description, " +
                "p.owner_id AS owner_id, " +
                "p.creation_date AS plant_creation_date, " +
                "u.id AS user_id, " +
                "u.first_name, " +
                "u.last_name, " +
                "u.birth_date, " +
                "u.user_role, " +
                "u.email, " +
                "e.id AS environment_id, " +
                "e.name AS environment_name, " +
                "e.description AS environment_description, " +
                "ssp.id AS static_sensor_id, " +
                "ssp.name AS static_name, " +
                "ssp.description AS static_description, " +
                "ssp.priority AS static_priority, " +
                "ssp.output_identifier AS static_output_identifier, " +
                "ssp.last_measurement_value AS static_last_measurement_value, " +
                "ssp.unit_of_measurement AS static_unit_of_measurement, " +
                "ssp.threshold_type AS static_threshold_type, " +
                "ss.threshold_offset AS threshold_offset, " +
                "csp.id AS calibrated_sensor_id, " +
                "csp.name AS calibrated_name, " +
                "csp.description AS calibrated_description, " +
                "csp.priority AS calibrated_priority, " +
                "csp.output_identifier AS calibrated_output_identifier, " +
                "csp.last_measurement_value AS calibrated_last_measurement_value, " +
                "csp.unit_of_measurement AS calibrated_unit_of_measurement, " +
                "csp.threshold_type AS calibrated_threshold_type, " +
                "cs.max_value AS max_value, " +
                "cs.min_value AS min_value, " +
                "cs.percentage_threshold AS percentage_threshold, " +
                "a.id AS actuator_id, " +
                "a.name AS actuator_name, " +
                "a.description AS actuator_description, " +
                "a.priority AS actuator_priority, " +
                "a.input_identifier AS input_identifier " +
                "FROM plant p " +
                "LEFT JOIN " +
                "\"USER\" u ON " +
                "p.owner_id = u.id " +
                "LEFT JOIN " +
                "environment e ON " +
                "p.environment_id = e.id " +
                "" +
                "LEFT JOIN " +
                "plant_static_sensor pss ON " +
                "p.id = pss.plant_id " +
                "LEFT JOIN " +
                "static_sensor ss ON " +
                "pss.static_sensor_id = ss.id " +
                "LEFT JOIN " +
                "sensor ssp ON " +
                "ss.id = ssp.id " +
                "" +
                "LEFT JOIN " +
                "plant_calibrated_sensor pcs ON " +
                "p.id = pcs.plant_id " +
                "LEFT JOIN " +
                "calibrated_sensor cs ON " +
                "pcs.calibrated_sensor_id = cs.id " +
                "LEFT JOIN " +
                "sensor csp ON " +
                "cs.id = csp.id " +
                "LEFT JOIN " +
                "actuator a ON " +
                "csp.actuator_id = a.id";

        return jdbcTemplateHelper.query(query, plantResultSetExtractor());
    }
    @Override
    public Integer updatePlant(Plant plant) {
        String query = "" +
                "UPDATE plant " +
                "SET name = ?, " +
                "description = ?, " +
                "creation_date = ? " +
                "WHERE id = ?";

        return jdbcTemplateHelper.update(
                query,
                plant.getName(),
                plant.getDescription(),
                plant.getCreationDate(),
                plant.getId());
    }

    @Override
    public Integer deletePlant(Integer id) {
        String query = "" +
                "DELETE FROM plant " +
                "WHERE id = ?";
        return jdbcTemplateHelper.update(query, id);
    }

    @Override
    public void attachStaticSensor(Integer plantId, Integer staticSensorId) {
        String query = "" +
                "INSERT INTO plant_static_sensor " +
                "(plant_id, static_sensor_id) " +
                "SELECT ?, ? " +
                "FROM DUAL " +
                "WHERE NOT EXISTS " +
                "(SELECT * FROM plant_static_sensor " +
                "WHERE plant_id = ? AND static_sensor_id = ?)" ;
        jdbcTemplateHelper.update(query, plantId, staticSensorId, plantId, staticSensorId);
    }

    @Override
    public void attachCalibratedSensor(Integer plantId, Integer calibratedSensorId) {
        String query = "" +
                "INSERT INTO plant_calibrated_sensor " +
                "(plant_id, calibrated_sensor_id) " +
                "SELECT ?, ? " +
                "FROM DUAL " +
                "WHERE NOT EXISTS " +
                "(SELECT * FROM plant_calibrated_sensor " +
                "WHERE plant_id = ? AND calibrated_sensor_id = ?)" ;
        jdbcTemplateHelper.update(query, plantId, calibratedSensorId, plantId, calibratedSensorId);
    }

    // Mappers

    ResultSetExtractor<List<Plant>> plantResultSetExtractor()
    {
        ResultSetExtractor<List<Plant>> resultSetExtractor = (resultSet) -> {
            Map<Integer, CalibratedSensor> calibratedSensorsMap = new HashMap<>();
            Map<Integer, StaticSensor> staticSensorsMap = new HashMap<>();
            Map<Integer, Plant> plants = new HashMap<>();
            while(resultSet.next()) {
                Integer plantId = resultSet.getInt("plant_id");
                Plant plant = plants.get(plantId);
                if (plantId != null && plant == null) {
                    calibratedSensorsMap = new HashMap<>();
                    staticSensorsMap = new HashMap<>();
                    Integer userId = resultSet.getInt("user_id");
                    userId = resultSet.wasNull() ? null : userId;
                    User owner = null;
                    if (userId != null) {
                        owner = new User(resultSet.getInt("user_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getDate("birth_date"),
                                resultSet.getString("user_role"),
                                resultSet.getString("email"),
                                null);
                    }

                    String environmentDescription = resultSet.getString("environment_description");
                    environmentDescription = resultSet.wasNull() ? null : environmentDescription;
                    Integer environmentId = resultSet.getInt("environment_id");
                    environmentId = resultSet.wasNull() ? null : environmentId;
                    Environment environment = null;
                    if (environmentId != null) {
                        environment = new Environment(environmentId,
                                resultSet.getString("environment_name"),
                                environmentDescription);
                    }

                    String description = resultSet.getString("plant_description");
                    description = resultSet.wasNull() ? null : description;
                    Date creationDate = resultSet.getDate("plant_creation_date");
                    plant = new Plant(resultSet.getInt("plant_id"),
                            resultSet.getString("plant_name"),
                            description,
                            environment,
                            owner,
                            null,
                            creationDate);
                    plants.put(plantId, plant);
                }
                Integer staticSensorId = resultSet.getInt("static_sensor_id");
                if(!resultSet.wasNull() && !staticSensorsMap.containsKey(staticSensorId))
                {
                    Double lastMeasurementValue = resultSet.getDouble("static_last_measurement_value");
                    lastMeasurementValue = resultSet.wasNull() ? null : lastMeasurementValue;

                    Double thresholdOffset = resultSet.getDouble("threshold_offset");
                    thresholdOffset = resultSet.wasNull() ? null : thresholdOffset;

                    Integer actuatorId = resultSet.getInt("actuator_id");
                    actuatorId = resultSet.wasNull() ? null : actuatorId;
                    Actuator actuator = null;
                    if(actuatorId != null) {
                        actuator = new Actuator(resultSet.getInt("actuator_id"),
                                resultSet.getString("actuator_name"),
                                resultSet.getString("actuator_description"),
                                resultSet.getString("actuator_priority"),
                                resultSet.getString("input_identifier"));
                    }

                    StaticSensor staticSensor = new StaticSensor(
                            staticSensorId,
                            resultSet.getString("static_name"),
                            resultSet.getString("static_description"),
                            resultSet.getString("static_priority"),
                            resultSet.getString("static_output_identifier"),
                            resultSet.getString("static_unit_of_measurement"),
                            lastMeasurementValue,
                            resultSet.getString("static_threshold_type"),
                            actuator,
                            thresholdOffset
                    );
                    staticSensorsMap.put(staticSensor.getId(), staticSensor);
                    plant.setStaticSensorsList(new ArrayList<>(staticSensorsMap.values()));
                }

                Integer calibratedSensorId = resultSet.getInt("calibrated_sensor_id");
                if(!resultSet.wasNull() && !calibratedSensorsMap.containsKey(calibratedSensorId))
                {
                    Double lastMeasurementValue = resultSet.getDouble("calibrated_last_measurement_value");
                    lastMeasurementValue = resultSet.wasNull() ? null : lastMeasurementValue;

                    Double maxValue = resultSet.getDouble("max_value");
                    maxValue = resultSet.wasNull() ? null : maxValue;

                    Double minValue = resultSet.getDouble("min_value");
                    minValue = resultSet.wasNull() ? null : minValue;

                    Double percentage_threshold = resultSet.getDouble("percentage_threshold");
                    percentage_threshold = resultSet.wasNull() ? null : percentage_threshold;

                    Integer actuatorId = resultSet.getInt("actuator_id");
                    actuatorId = resultSet.wasNull() ? null : actuatorId;
                    Actuator actuator = null;
                    if(actuatorId != null) {
                        actuator = new Actuator(resultSet.getInt("actuator_id"),
                                resultSet.getString("actuator_name"),
                                resultSet.getString("actuator_description"),
                                resultSet.getString("actuator_priority"),
                                resultSet.getString("input_identifier"));
                    }


                    CalibratedSensor calibratedSensor = new CalibratedSensor(
                            calibratedSensorId,
                            resultSet.getString("calibrated_name"),
                            resultSet.getString("calibrated_description"),
                            resultSet.getString("calibrated_priority"),
                            resultSet.getString("calibrated_output_identifier"),
                            resultSet.getString("calibrated_unit_of_measurement"),
                            lastMeasurementValue,
                            resultSet.getString("calibrated_threshold_type"),
                            actuator,
                            maxValue,
                            minValue,
                            percentage_threshold
                    );
                    calibratedSensorsMap.put(calibratedSensor.getId(), calibratedSensor);
                    plant.setCalibratedSensorsList(new ArrayList<>(calibratedSensorsMap.values()));
                }
            }
            return new ArrayList<Plant>(plants.values());
        };
        return resultSetExtractor;
    }
}
