package com.floralstack.floralstackbackend.user;

import com.floralstack.floralstackbackend.sensor.StaticSensor;
import com.floralstack.floralstackbackend.utilities.JdbcTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;

@Repository
public class UserDataAccessService implements UserDataAccessServiceProvider{
    private final JdbcTemplateHelper jdbcTemplateHelper;

    @Autowired
    public UserDataAccessService(JdbcTemplateHelper jdbcTemplateHelper) {
        this.jdbcTemplateHelper = jdbcTemplateHelper;
    }

    @Override
    public Integer createUser(@Valid User user) {
        String query = "" +
                "INSERT INTO \"USER\" (" +
                "first_name, " +
                "last_name, " +
                "birth_date, " +
                "user_role, " +
                "email, " +
                "user_password ) " +
                "VALUES (?, ?, ?, ?, ?, ?) ";
        return jdbcTemplateHelper.update(
                query,
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getUserRole(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public User getUser(Integer id) {
        String query = "" +
                "SELECT *" +
                " FROM \"USER\"" +
                " WHERE" +
                " \"USER\".id = ?";

        return jdbcTemplateHelper.queryForObject(query, mapUser(), id);
    }

    @Override
    public List<User> getAllUsers() {
        String query = "" +
                "SELECT *" +
                " FROM \"USER\"";

        return jdbcTemplateHelper.query(query, mapUser());
    }

    @Override
    public Integer updateUser(User user) {
        String query = "" +
                "UPDATE \"USER\"" +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "birth_date = ?, " +
                "user_role = ? " +
                "WHERE \"USER\".id = ?";


        return jdbcTemplateHelper.update(
                query,
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getUserRole(),
                user.getId()
        );
    }

    @Override
    public Integer deleteUser(Integer id) {
        String query = "" +
                "DELETE FROM \"USER\"" +
                "WHERE \"USER\".id = ? ";

        return jdbcTemplateHelper.update(query, id);
    }


    // MAPPERS

    private RowMapper<User> mapUser() {
        RowMapper<User> rowMapper = (resultSet, i) -> {
            User user =  new User(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("birth_date"),
                    resultSet.getString("user_role"),
                    resultSet.getString("email"),
                    resultSet.getString("user_password")
            );
            return user;
        };
        return rowMapper;
    }
}
