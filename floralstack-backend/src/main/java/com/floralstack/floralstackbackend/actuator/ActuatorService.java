package com.floralstack.floralstackbackend.actuator;


import com.floralstack.floralstackbackend.environment.EnvironmentDataAccessServiceProvider;
import com.floralstack.floralstackbackend.exception.ApiRequestExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActuatorService {
    private final ActuatorDataAccessServiceProvider actuatorDataAccessServiceProvider;

    @Autowired
    public ActuatorService(ActuatorDataAccessServiceProvider actuatorDataAccessServiceProvider) {
        this.actuatorDataAccessServiceProvider = actuatorDataAccessServiceProvider;
    }

    public void createActuator(Actuator actuator) {
        actuatorDataAccessServiceProvider.createActuator(actuator);
    }

    public Actuator getActuator(Integer id) {
        return actuatorDataAccessServiceProvider.getActuator(id);
    }

    public List<Actuator> getAllActuators() {
        return actuatorDataAccessServiceProvider.getAllActuators();
    }

    public void updateActuator(Actuator actuator) {
        Integer update = actuatorDataAccessServiceProvider.updateActuator(actuator);
        if(update == 0)
        {
            throw ApiRequestExceptionFactory.failedUpdateException;
        }
    }

    public void deleteActuator(Integer id) {
        Integer delete = actuatorDataAccessServiceProvider.deleteActuator(id);
        if(delete == 0)
        {
            throw ApiRequestExceptionFactory.failedDeleteException;
        }
    }
}
