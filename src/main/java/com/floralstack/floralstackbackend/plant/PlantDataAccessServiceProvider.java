package com.floralstack.floralstackbackend.plant;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface PlantDataAccessServiceProvider {
    int createPlant(Plant plant, Date creationDate);
    int createDetailedPlant(Plant.Create plant, Date currentDate);
    Plant getPlant(Integer id);
    List<Plant> getPlantsForOwner(Integer id);
    List<Plant> getAllPlantsForEnvironment(Integer id);
    List<Plant> getAllPlants();
    List<Plant> getPlantsBatch(Integer offset, Integer batchSize);
    Integer getAllPlantsCount();
    Integer updatePlant(Plant plant);
    Integer updatePlant(Plant.Update plant);
    Integer deletePlant(Integer id);
    void attachStaticSensor(Integer plantId, Integer staticSensorId);
    void attachCalibratedSensor(Integer plantId, Integer calibratedSensorId);
    Integer detachStaticSensor(Integer plantId, Integer staticSensorId);
    Integer detachCalibratedSensor(Integer plantId, Integer staticSensorId);
}
