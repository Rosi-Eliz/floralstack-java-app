package com.floralstack.floralstackbackend.user;

import com.floralstack.floralstackbackend.sensor.StaticSensor;

import java.util.List;

public interface UserDataAccessServiceProvider {
    Integer createUser(User user);
    User getUser(Integer id);
    List<User> getAllUsers();
    Integer updateUser(User user);
    Integer deleteUser(Integer id);
}
