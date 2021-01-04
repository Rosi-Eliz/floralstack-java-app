package com.floralstack.floralstackbackend.plant;

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
                " environment_id, " +
                " owner_id," +
                " creation_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                query,
                plant.getName(),
                plant.getDescription(),
                plant.getEnvironmentID(),
                plant.getOwnerID(),
                creationDate
        );
    }

    @Override
    public Plant getPlant(Integer id) {
        String query = "" +
                "SELECT *" +
                "FROM plant " +
                "WHERE " +
                "id = ?";
        return jdbcTemplate.queryForObject(query, mapPlantFomDb(), id);
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
        return jdbcTemplate.query(query, mapPlantFomDb(), id);
    }


    @Override
    public List<Plant> getAllPlantsForEnvironment(Integer id) {
        String query = "" +
                "SELECT * FROM plant" +
                "WHERE environment_id = ?";
        return jdbcTemplate.query(query, mapPlantFomDb(), id);
    }

    @Override
    public List<Plant> getAllPlants() {
        String query = "" +
                "SELECT " +
                "id, " +
                "name, " +
                "description, " +
                "owner_id, " +
                "environment_id, " +
                "creation_date " +
                "FROM plant ";
        return jdbcTemplate.query(query, mapPlantFomDb());
    }

    @Override
    public Integer updatePlant(Plant plant) {
        String query = "" +
                "UPDATE plant " +
                "SET name = ?, " +
                "description = ?, " +
                "owner_id = ?, " +
                "environment_id = ?, " +
                "creation_date = ? " +
                "WHERE id = ?";

        return jdbcTemplate.update(
                query,
                plant.getName(),
                plant.getDescription(),
                plant.getOwnerID(),
                plant.getEnvironmentID(),
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

    private RowMapper<Plant> mapPlantFomDb() {
        RowMapper<Plant> rowMapper = (resultSet, i) -> {
            String description = resultSet.getString("description");
            description = resultSet.wasNull() ? null : description;
            Integer ownerID = resultSet.getInt("owner_id");
            ownerID = resultSet.wasNull() ? null : ownerID;
            Integer environmentID = resultSet.getInt("environment_id");
            environmentID = resultSet.wasNull() ? null : environmentID;
            Date creationDate = resultSet.getDate("creation_date");

            Plant plant =  new Plant(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    description,
                    ownerID,
                    environmentID,
                    creationDate);
            return plant;
        };
        return rowMapper;
    }
}
