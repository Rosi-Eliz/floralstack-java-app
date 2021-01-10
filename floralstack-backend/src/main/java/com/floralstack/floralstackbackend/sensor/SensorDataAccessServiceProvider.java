package com.floralstack.floralstackbackend.sensor;

import java.util.List;

public interface SensorDataAccessServiceProvider {
    SensorDataAccessService.CreateSensorResult createSensor(Sensor sensor);
    Integer createStaticSensor(StaticSensor staticSensor);
    List<StaticSensor> getAllStaticSensors();
    StaticSensor getStaticSensor(Integer id);
    void updateStaticSensor(StaticSensor sensor);
}
