package com.floralstack.floralstackbackend.plant;

import com.floralstack.floralstackbackend.commons.IdentityRequestModel;
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
    public void createDetailedPlant(@RequestBody @Valid Plant.Create plant){
        plantService.createDetailedPlant(plant);
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

    @PostMapping("update")
    public void updatePlant(@RequestBody @Valid Plant.Update plant){
        plantService.updatePlant(plant);
    }

    @DeleteMapping(path = "{id}")
    public void deletePlant(@PathVariable("id") Integer id) {
        plantService.deletePlant(id);
    }

    @PostMapping(path = "delete/{id}")
    public void postDeletePlant(@PathVariable("id") Integer id) {
        plantService.deletePlant(id);
    }

    @PostMapping(path = "{id}/attach_static_sensor")
    public void attachStaticSensor(@PathVariable("id") Integer id, @RequestBody @Valid IdentityRequestModel identityRequestModel){
        plantService.attachStaticSensor(id, identityRequestModel.getId());
    }
    @PostMapping(path = "{id}/attach_calibrated_sensor")
    public void attachCalibratedSensor(@PathVariable("id") Integer id, @RequestBody @Valid IdentityRequestModel identityRequestModel){
        plantService.attachCalibratedSensor(id, identityRequestModel.getId());
    }
    @PostMapping(path = "{id}/detach_calibrated_sensor")
    public void detachCalibratedSensor(@PathVariable("id") Integer id, @RequestBody @Valid IdentityRequestModel identityRequestModel){
        plantService.detachCalibratedSensor(id, identityRequestModel.getId());
    }

    @PostMapping(path = "{id}/detach_static_sensor")
    public void detachStaticSensor(@PathVariable("id") Integer id, @RequestBody @Valid IdentityRequestModel identityRequestModel){
        plantService.detachStaticSensor(id, identityRequestModel.getId());
    }
}
