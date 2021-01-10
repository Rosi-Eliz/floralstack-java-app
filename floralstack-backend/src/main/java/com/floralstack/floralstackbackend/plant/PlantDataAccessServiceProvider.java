package com.floralstack.floralstackbackend.plant;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface PlantDataAccessServiceProvider {
    int createPlant(Plant plant, Date creationDate);
    Plant getPlant(Integer id);
    List<Plant> getPlantsForOwner(Integer id);
    List<Plant> getAllPlantsForEnvironment(Integer id);
    List<Plant> getAllPlants();
    Integer updatePlant(Plant plant);
    Integer deletePlant(Integer id);
    void attachStaticSensor(Integer plantId, Integer staticSensorId);
}
