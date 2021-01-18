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

    //Static sensor methods:

    public void createStaticSensor(StaticSensor staticSensor) {
        CreateSensorResult result = sensorDataAccessService.createSensor(staticSensor);
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

    public StaticSensor getStaticSensor(Integer id) {
        return sensorDataAccessService.getStaticSensor(id);
    }

    //Calibrated sensor methods

    public void createCalibratedSensor(CalibratedSensor calibratedSensor) {
        CreateSensorResult result = sensorDataAccessService.createSensor(calibratedSensor);
        if(result.getQueryResult() == 0)
        {
            throw ApiRequestExceptionFactory.failedCreationException;
        }
        calibratedSensor.setId(result.getCreatedSensorId());
        Integer insertion = sensorDataAccessService.createCalibratedSensor(calibratedSensor);
        if(insertion == 0)
        {
            throw ApiRequestExceptionFactory.failedCreationException;
        }
    }

    public List<CalibratedSensor> getAllCalibratedSensors() {
        return sensorDataAccessService.getAllCalibratedSensors();
    }

    public CalibratedSensor getCalibratedSensor(Integer id) {
        return sensorDataAccessService.getCalibratedSensor(id);
    }
    //Common methods
    public void updateSensor(Sensor sensor) {
        if (sensor.getId() == null) {
            throw ApiRequestExceptionFactory.missingIdException;
        }
        Integer update = sensorDataAccessService.updateSensor(sensor);
        if (update == 0) {
            throw ApiRequestExceptionFactory.failedUpdateException;
        }
    }

    public void deleteSensor(Integer id) {
        Integer delete = sensorDataAccessService.deleteSensor(id);
        if (delete == 0) {
            throw ApiRequestExceptionFactory.failedDeleteException;
        }
    }

    public List<StaticSensor> getAllStaticUnattachedSensors() {
        return sensorDataAccessService.getAllUnattachedStaticSensors();
    }

    public List<CalibratedSensor> getAllCalibratedUnattachedSensors() {
        return sensorDataAccessService.getAllUnattachedCalibratedSensors();
    }

    public void attachActuator(Integer id, Integer id1) {
         sensorDataAccessService.attachActuator(id, id1);
    }

    public void detachActuator(Integer id, Integer id1) {
        sensorDataAccessService.detachActuator(id, id1);
    }
}
