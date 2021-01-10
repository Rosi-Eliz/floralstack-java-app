package com.floralstack.floralstackbackend.sensor;

import com.floralstack.floralstackbackend.exception.ApiRequestExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    SensorDataAccessService sensorDataAccessService;

    @Autowired
    public SensorService(SensorDataAccessService sensorDataAccessService) {
        this.sensorDataAccessService = sensorDataAccessService;
    }

    public void createStaticSensor(StaticSensor staticSensor) {
        SensorDataAccessService.CreateSensorResult result = sensorDataAccessService.createSensor(staticSensor);
        if(result.getQueryResult() == 0)
        {
            throw ApiRequestExceptionFactory.failedCreationException;
        }
        staticSensor.setId(result.getCreatedSensorId());
        Integer insertion = sensorDataAccessService.createStaticSensor(staticSensor);
        if(insertion == 0)
        {
            throw ApiRequestExceptionFactory.failedCreationException;
        }
    }

    public List<StaticSensor> getAllStaticSensors() {
        return sensorDataAccessService.getAllStaticSensors();
    }
}
