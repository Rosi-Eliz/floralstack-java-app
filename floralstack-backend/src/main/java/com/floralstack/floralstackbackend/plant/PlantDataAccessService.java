package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class PlantDataAccessService implements PlantDataAccessServiceProvider{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PlantDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private int insertPlant(@Valid Plant plant) {

        String sql = "" +
                "INSERT INTO plant (" +
                " id, " +
                " name, " +
                " description, " +
                " environment_ID, " +
                " owner_ID, " +
                " action_record_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                plant.getId(),
                plant.getName(),
                plant.getDescription(),
                plant.getOwner_ID(),
                plant.getEnvironment_ID(),
                plant.getAction_record_ID()
        );
    }

    @Override
    public void addPlant(@Valid Plant plant) {
        insertPlant(plant);
    }
}
