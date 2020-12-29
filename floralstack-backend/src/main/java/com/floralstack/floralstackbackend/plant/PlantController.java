package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/plants")
public class PlantController {
    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;

    }
    @PostMapping
    public void addPlant(@RequestBody @Valid Plant plant){
        plantService.createPlant(plant);
    }

    @GetMapping(path = "{id}")
    public Plant getPlant(@PathVariable("id") Integer id) {
        return plantService.getPlant(id);
    }
}
