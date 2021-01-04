package com.floralstack.floralstackbackend.plant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @GetMapping(path = "owner/{id}")
    public List<Plant> getPlantsForOwner(@PathVariable("id") Integer id) {
        return plantService.getPlantsForOwner(id);
    }

    @GetMapping(path = "environment/{id}")
    public List<Plant> getAllPlantsForEnvironment(@PathVariable("id") Integer id) {
        return plantService.getAllPlantsForEnvironment(id);
    }

    @PutMapping
    public void updatePlant(@RequestBody @Valid Plant plant){
        plantService.updatePlant(plant);
    }

    @DeleteMapping(path = "{id}")
    public void deletePlant(@PathVariable("id") Integer id) {
        plantService.deletePlant(id);
    }
}
