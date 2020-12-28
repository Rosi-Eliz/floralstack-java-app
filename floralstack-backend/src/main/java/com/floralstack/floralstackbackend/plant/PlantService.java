package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantService {

    private final PlantDataAccessServiceProvider plantDataAccessServiceProvider;

    @Autowired
    public PlantService(PlantDataAccessServiceProvider plantDataAccessServiceProvider) {
        this.plantDataAccessServiceProvider = plantDataAccessServiceProvider;
    }

    void createPlant(Plant plant) {
        plantDataAccessServiceProvider.createPlant(plant);
    }
}
