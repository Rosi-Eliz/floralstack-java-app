package com.floralstack.floralstackbackend.plant;

import com.floralstack.floralstackbackend.environment.Environment;
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
                "p.creation_date AS plant_creation_date, " +
                "u.id AS user_id, " +
                "u.first_name, " +
                "u.last_name, " +
                "u.birth_date, " +
                "u.user_role, " +
                "u.email, " +
                "e.id AS environment_id, " +
                "e.name AS environment_name, " +
                "e.description AS environment_description " +
                "FROM plant p " +
                "LEFT JOIN " +
                "\"USER\" u ON " +
                "p.owner_id = u.id " +
                "LEFT JOIN " +
                "environment e ON " +
                "p.environment_id = e.id " +
                "WHERE p.id = ?";

        return jdbcTemplateHelper.queryForObject(query, plantRowMapper(), id);
    }

    @Override
    public List<Plant> getPlantsForOwner(Integer id) {
        String query = "" +
                "SELECT " +
                "id, " +
                "name, " +
                "description, " +
                "owner_id, " +
                "environment_id, " +
                "creation_date " +
                "FROM plant " +
                "WHERE " +
                "owner_id = ?";
        return jdbcTemplateHelper.query(query, plantRowMapper(), id);
    }


    @Override
    public List<Plant> getAllPlantsForEnvironment(Integer id) {
        String query = "" +
                "SELECT * FROM plant" +
                "WHERE environment_id = ?";
        return jdbcTemplateHelper.query(query, plantRowMapper(), id);
    }

    @Override
    public List<Plant> getAllPlants() {
        String query = "" +
                "SELECT " +
                "p.id AS plant_id, " +
                "p.name AS plant_name, " +
                "p.description AS plant_description, " +
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
                "ss.threshold_offset AS threshold_offset " +
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
                "cs.id = csp.id ";

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

    // Mappers

    private RowMapper<Plant> plantRowMapper() {
        RowMapper<Plant> rowMapper = (resultSet, i) -> {
            Integer userId = resultSet.getInt("user_id");
            userId = resultSet.wasNull() ? null : userId;
            User owner = null;
            if(userId != null) {
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
            if(environmentId != null) {
                environment = new Environment(environmentId,
                        resultSet.getString("environment_name"),
                        environmentDescription);
            }

            String description = resultSet.getString("plant_description");
            description = resultSet.wasNull() ? null : description;
            Date creationDate = resultSet.getDate("plant_creation_date");
            Plant plant =  new Plant(resultSet.getInt("plant_id"),
                    resultSet.getString("plant_name"),
                    description,
                    environment,
                    owner,
                    null,
                    creationDate);
            return plant;
        };
        return rowMapper;
    }

    ResultSetExtractor<List<Plant>> plantResultSetExtractor()
    {
        ResultSetExtractor<List<Plant>> resultSetExtractor = (resultSet) -> {
            List<StaticSensor> staticSensors = new ArrayList<>();
            Map<Integer, Plant> plants = new HashMap<>();
            while(resultSet.next()) {
                Integer plantId = resultSet.getInt("plant_id");
                Plant plant = plants.get(plantId);
                if (plantId != null && plant == null) {
                    staticSensors = new ArrayList<>();
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
                if(!resultSet.wasNull())
                {
                    Double lastMeasurementValue = resultSet.getDouble("static_last_measurement_value");
                    lastMeasurementValue = resultSet.wasNull() ? null : lastMeasurementValue;

                    Double thresholdOffset = resultSet.getDouble("threshold_offset");
                    thresholdOffset = resultSet.wasNull() ? null : thresholdOffset;

                    StaticSensor staticSensor = new StaticSensor(
                            staticSensorId,
                            resultSet.getString("static_name"),
                            resultSet.getString("static_description"),
                            resultSet.getString("static_priority"),
                            resultSet.getString("static_output_identifier"),
                            resultSet.getString("static_unit_of_measurement"),
                            lastMeasurementValue,
                            resultSet.getString("static_threshold_type"),
                            thresholdOffset
                    );
                    staticSensors.add(staticSensor);
                    plant.setStaticSensorsList(staticSensors);
                }
            }
            return new ArrayList<Plant>(plants.values());
        };
        return resultSetExtractor;
    }
}
