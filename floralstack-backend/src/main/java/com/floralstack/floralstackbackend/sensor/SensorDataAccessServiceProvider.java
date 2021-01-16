package com.floralstack.floralstackbackend.sensor;

import java.util.List;

public interface SensorDataAccessServiceProvider {
    SensorDataAccessService.CreateSensorResult createSensor(Sensor sensor);
    Integer createStaticSensor(StaticSensor staticSensor);
    List<StaticSensor> getAllStaticSensors();
    StaticSensor getStaticSensor(Integer id);
    Integer updateSensor(Sensor sensor);
    Integer deleteSensor(Integer id);
    Integer createCalibratedSensor(CalibratedSensor calibratedSensor);
    List<CalibratedSensor> getAllCalibratedSensors();
    CalibratedSensor getCalibratedSensor(Integer id);
}
