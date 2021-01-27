package com.floralstack.floralstackbackend.resource;

import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.plant.Plant;
import com.floralstack.floralstackbackend.plant.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/resources")
public class ResourceController {
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public void createResource(@RequestBody @Valid Resource resource){
        resourceService.createResource(resource);
    }

    @GetMapping(path = "{id}")
    public Resource getResource(@PathVariable("id") Integer id) {
        return resourceService.getResource(id);
    }

    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @PutMapping
    public void updateEnvironment(@RequestBody @Valid Resource environment){
        resourceService.updateResource(environment);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEnvironment(@PathVariable("id") Integer id) {

        resourceService.deleteResource(id);
    }

}
