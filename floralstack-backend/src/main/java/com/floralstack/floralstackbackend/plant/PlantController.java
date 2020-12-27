package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/plants")
public class PlantController {
    private final PlantDataAccessServiceProvider plantService;

    @Autowired
    public PlantController(PlantDataAccessServiceProvider plantService) {
        this.plantService = plantService;

    }
    @PostMapping
    public void addPlant(@RequestBody @Valid Plant plant){
        plantService.addPlant(plant);
    }
}
