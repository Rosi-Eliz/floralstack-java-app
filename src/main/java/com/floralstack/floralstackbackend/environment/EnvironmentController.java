package com.floralstack.floralstackbackend.environment;

import com.floralstack.floralstackbackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/environments")
public class EnvironmentController {
    private final EnvironmentService environmentService;

    @Autowired
    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping
    public void createEnvironment(@RequestBody @Valid Environment environment){
        environmentService.createEnvironment(environment);
    }

    @GetMapping(path = "{id}")
    public Environment getEnvironment(@PathVariable("id") Integer id) {
        return environmentService.getEnvironment(id);
    }

    @GetMapping
    public List<Environment> getAllEnvironments() {return environmentService.getAllEnvironments();}

    @PutMapping
    public void updateEnvironment(@RequestBody @Valid Environment environment){
        environmentService.updateEnvironment(environment);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEnvironment(@PathVariable("id") Integer id) {
        environmentService.deleteEnvironment(id);
    }
}
