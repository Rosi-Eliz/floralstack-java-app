package com.floralstack.floralstackbackend.sensor;

import java.util.List;

public interface SensorDataAccessServiceProvider {
    SensorDataAccessService.CreateSensorResult createSensor(Sensor sensor);
    Integer createStaticSensor(StaticSensor staticSensor);
    List<StaticSensor> getAllStaticSensors();
    List<StaticSensor> getAllUnattachedStaticSensors();
    StaticSensor getStaticSensor(Integer id);
    Integer updateSensor(Sensor sensor);
    Integer deleteSensor(Integer id);
    Integer createCalibratedSensor(CalibratedSensor calibratedSensor);
    List<CalibratedSensor> getAllCalibratedSensors();
    List<CalibratedSensor> getAllUnattachedCalibratedSensors();
    CalibratedSensor getCalibratedSensor(Integer id);
    void attachActuator(Integer id, Integer id1);
}
