package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import  com.floralstack.floralstackbackend.exception.*;

@Service
public class PlantService {

    private final PlantDataAccessServiceProvider plantDataAccessServiceProvider;

    @Autowired
    public PlantService(PlantDataAccessServiceProvider plantDataAccessServiceProvider) {
        this.plantDataAccessServiceProvider = plantDataAccessServiceProvider;
    }

    void createPlant(Plant plant) { plantDataAccessServiceProvider.createPlant(plant); }

    void updatePlant(Plant plant) {
        if (plant.getId() == null) {
            throw new ApiRequestException("missing required parameter id");
        }
        plantDataAccessServiceProvider.updatePlant(plant);
    }

    Plant getPlant(Integer id) { return plantDataAccessServiceProvider.getPlant(id); };

    List<Plant> getAllPlants() { return plantDataAccessServiceProvider.getAllPlants(); }

    List<Plant> getPlantsForOwner(Integer id) { return plantDataAccessServiceProvider.getPlantsForOwner(id); }

    void deletePlant(Integer id) { plantDataAccessServiceProvider.deletePlant(id); }
}
