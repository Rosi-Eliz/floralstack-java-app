package com.floralstack.floralstackbackend.resource;

import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceDataAccessService implements ResourceDataAccessServiceProvider{
    private final JdbcTemplateHelper jdbcTemplateHelper;

    @Autowired
    public ResourceDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
    }

    @Override
    public Integer createResource(Resource resource) {

        String query = "" +
                "INSERT INTO \"RESOURCE\" (" +
                " sensor_id," +
                " name, " +
                " description, " +
                " unit_of_measurement," +
                " drawn_amount," +
                " current_amount" +
                ") " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplateHelper.update(
                query,
                resource.getSensorId(),
                resource.getName(),
                resource.getDescription(),
                resource.getUnitOfMeasurement(),
                resource.getDrawnAmount(),
                resource.getCurrentAmount()
        );
    }

    @Override
    public Resource getResource(Integer id) {
        String query = "" +
                "SELECT * FROM \"RESOURCE\"" +
                "WHERE id = ?";

        return jdbcTemplateHelper.queryForObject(query,mapResource(), id);
    }

    @Override
    public List<Resource> getAllResources() {

        String query = "" +
                "SELECT * FROM \"RESOURCE\"";

        return jdbcTemplateHelper.query(query, mapResource());
    }

    @Override
    public Integer updateResource(Resource resource) {
        String query = "" +
                "UPDATE \"RESOURCE\" " +
                "SET name = ?, " +
                "description = ? " +
                "unit_of_measurement = ?" +
                "drawn_amount = ?" +
                "current_amount = ?" +
                "WHERE resource.id = ?";

        return jdbcTemplateHelper.update(
                query,
                resource.getName(),
                resource.getDescription(),
                resource.getUnitOfMeasurement(),
                resource.getDrawnAmount(),
                resource.getCurrentAmount(),
                resource.getId());
    }

    @Override
    public Integer deleteResource(Integer id) {
        String query = "" +
                "DELETE FROM \"RESOURCE\" " +
                "WHERE \"RESOURCE\".id = ?";

        return jdbcTemplateHelper.update(query, id);
    }

    // MAPPERS
    private RowMapper<Resource> mapResource() {
        RowMapper<Resource> rowMapper = (resultSet, i) -> {
            String description = resultSet.getString("description");
            description = resultSet.wasNull() ? null : description;

            Resource resource =  new Resource(
                    resultSet.getInt("id"),
                    resultSet.getInt("sensor_id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("unit_of_measurement"),
                    resultSet.getDouble("drawn_amount"),
                    resultSet.getDouble("current_amount")
            );
            return resource;
        };
        return rowMapper;
    }
}
