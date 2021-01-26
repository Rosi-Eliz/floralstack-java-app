package com.floralstack.floralstackbackend.actuator;

import com.floralstack.floralstackbackend.environment.Environment;

import java.util.List;

public interface ActuatorDataAccessServiceProvider {
    Integer createActuator(Actuator actuator);
    Actuator getActuator(Integer id);
    List<Actuator> getAllActuators();
    Integer updateActuator(Actuator actuator);
    Integer deleteActuator(Integer id);
}
