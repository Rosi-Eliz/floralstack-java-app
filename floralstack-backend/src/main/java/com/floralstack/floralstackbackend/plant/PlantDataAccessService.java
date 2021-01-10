package com.floralstack.floralstackbackend.plant;

import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

@Repository
public class PlantDataAccessService implements PlantDataAccessServiceProvider{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PlantDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPlant(@Valid Plant plant, Date creationDate) {

        String query = "" +
                "INSERT INTO plant (" +
                " name, " +
                " description, " +
                " creation_date) " +
                "VALUES (?, ?, ?)";

        return jdbcTemplate.update(
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

        return jdbcTemplate.queryForObject(query, plantRowMapper(), id);
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
        return jdbcTemplate.query(query, plantRowMapper(), id);
    }


    @Override
    public List<Plant> getAllPlantsForEnvironment(Integer id) {
        String query = "" +
                "SELECT * FROM plant" +
                "WHERE environment_id = ?";
        return jdbcTemplate.query(query, plantRowMapper(), id);
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
                "e.description AS environment_description " +
                "FROM plant p " +
                "LEFT JOIN " +
                "\"USER\" u ON " +
                "p.owner_id = u.id " +
                "LEFT JOIN " +
                "environment e ON " +
                "p.environment_id = e.id";
        return jdbcTemplate.query(query, plantRowMapper());
    }

    @Override
    public Integer updatePlant(Plant plant) {
        String query = "" +
                "UPDATE plant " +
                "SET name = ?, " +
                "description = ?, " +
                "creation_date = ? " +
                "WHERE id = ?";

        return jdbcTemplate.update(
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
        return jdbcTemplate.update(query, id);
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
                    creationDate);
            return plant;
        };
        return rowMapper;
    }
}
