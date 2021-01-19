package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import  com.floralstack.floralstackbackend.exception.*;

@Service
public class PlantService {

    private final PlantDataAccessServiceProvider plantDataAccessServiceProvider;

    @Autowired
    public PlantService(PlantDataAccessServiceProvider plantDataAccessServiceProvider) {
        this.plantDataAccessServiceProvider = plantDataAccessServiceProvider;
    }

    void createPlant(Plant plant) {
        Date currentDate = new Date();
        plantDataAccessServiceProvider.createPlant(plant, currentDate);
    }

    void updatePlant(Plant plant) {
        if (plant.getId() == null) {
            throw ApiRequestExceptionFactory.missingIdException;
        }
        Integer update = plantDataAccessServiceProvider.updatePlant(plant);
        if (update == 0) {
            throw ApiRequestExceptionFactory.failedUpdateException;
        }
    }

    void updatePlant(Plant.Update plant) {
        if (plant.getId() == null) {
            throw ApiRequestExceptionFactory.missingIdException;
        }
        Integer update = plantDataAccessServiceProvider.updatePlant(plant);
        if (update == 0) {
            throw ApiRequestExceptionFactory.failedUpdateException;
        }
    }

    Plant getPlant(Integer id) { return plantDataAccessServiceProvider.getPlant(id); };

    List<Plant> getAllPlants() { return plantDataAccessServiceProvider.getAllPlants(); }

    List<Plant> getPlantsForOwner(Integer id) { return plantDataAccessServiceProvider.getPlantsForOwner(id); }

    public List<Plant> getAllPlantsForEnvironment(Integer id) {
        return plantDataAccessServiceProvider.getAllPlantsForEnvironment(id);
    }

    public void deletePlant(Integer id) {
        Integer delete = plantDataAccessServiceProvider.deletePlant(id);
        if (delete == 0) {
            throw ApiRequestExceptionFactory.failedDeleteException;
        }
    }

    public void attachStaticSensor(Integer plantId, Integer staticSensorId) {
        plantDataAccessServiceProvider.attachStaticSensor(plantId, staticSensorId);
    }

    public void attachCalibratedSensor(Integer plantId, Integer calibratedSensorId) {
        plantDataAccessServiceProvider.attachCalibratedSensor(plantId, calibratedSensorId);
    }

    public void detachStaticSensor(Integer plantId, Integer staticSensorId) {
        Integer delete = plantDataAccessServiceProvider.detachStaticSensor(plantId, staticSensorId);
        if (delete == 0) {
            throw ApiRequestExceptionFactory.failedDeleteException;
        }
    }

    public void detachCalibratedSensor(Integer plantId, Integer calibratedSensorId) {
        Integer delete = plantDataAccessServiceProvider.detachCalibratedSensor(plantId, calibratedSensorId);
        if (delete == 0) {
            throw ApiRequestExceptionFactory.failedDeleteException;
        }
    }
}
