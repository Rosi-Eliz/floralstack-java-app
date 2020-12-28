package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;

@Repository
public class PlantDataAccessService implements PlantDataAccessServiceProvider{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PlantDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createPlant(@Valid Plant plant) {

        String sql = "" +
                "INSERT INTO plant (" +
                " name, " +
                " description, " +
                " environment_ID, " +
                " owner_ID, " +
                " action_record_ID) " +
                "VALUES (?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                plant.getName(),
                plant.getDescription(),
                plant.getEnvironment_ID(),
                plant.getOwner_ID(),
                plant.getAction_record_ID()
        );
    }
}
