package com.floralstack.floralstackbackend.plant;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PlantDataAccessServiceProvider {
    int createPlant(Plant plant);
    Plant getPlant(Integer id);
    List<Plant> getPlantsForOwner(Integer id);
    List<Plant> getAllPlants();
    int updatePlant(Plant plant);
    int deletePlant(Integer id);
}
