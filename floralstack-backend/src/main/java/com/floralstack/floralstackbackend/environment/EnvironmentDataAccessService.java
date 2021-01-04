package com.floralstack.floralstackbackend.environment;
import com.floralstack.floralstackbackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public class EnvironmentDataAccessService implements EnvironmentDataAccessServiceProvider{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EnvironmentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createEnvironment(@Valid Environment environment) {
        String query = "" +
                "INSERT INTO environment (" +
                "name, " +
                "description) " +
                "VALUES(?, ?)";

        return jdbcTemplate.update(
                query,
                environment.getName(),
                environment.getDescription()
                );
    }

    @Override
    public Environment getEnvironment(Integer id) {
        String query = "" +
                "SELECT * FROM environment " +
                "WHERE environment.id = ?";

        return jdbcTemplate.queryForObject(query, mapEnvironment(), id);
    }

    @Override
    public List<Environment> getAllEnvironments() {
        String query = "" +
                "SELECT * FROM environment";

        return jdbcTemplate.query(query, mapEnvironment());
    }

    @Override
    public Integer updateEnvironment(Environment environment) {
        String query = "" +
                "UPDATE environment " +
                "SET name = ?, " +
                "description = ? " +
                "WHERE environment.id = ?";

        return jdbcTemplate.update(
                query,
                environment.getName(),
                environment.getDescription(),
                environment.getId());
    }

    @Override
    public Integer deleteEnvironment(Integer id) {
        String query = "" +
                "DELETE FROM environment " +
                "WHERE environment.id = ?";

        return jdbcTemplate.update(query, id);
    }

    // MAPPERS
    private RowMapper<Environment> mapEnvironment() {
        RowMapper<Environment> rowMapper = (resultSet, i) -> {
            String description = resultSet.getString("description");
            description = resultSet.wasNull() ? null : description;

            Environment environment =  new Environment(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    description
            );
            return environment;
        };
        return rowMapper;
    }
}
