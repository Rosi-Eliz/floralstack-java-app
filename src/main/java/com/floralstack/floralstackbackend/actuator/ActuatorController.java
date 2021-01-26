package com.floralstack.floralstackbackend.actuator;


import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.environment.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/actuators")
public class ActuatorController {
    private final ActuatorService actuatorService;

    @Autowired
    public ActuatorController(ActuatorService actuatorService) {
        this.actuatorService = actuatorService;
    }

    @PostMapping
    public void createActuator(@RequestBody @Valid Actuator actuator){
        actuatorService.createActuator(actuator);
    }
    @GetMapping(path = "{id}")
    public Actuator getActuator(@PathVariable("id") Integer id) {
        return actuatorService.getActuator(id);
    }

    @GetMapping
    public List<Actuator> getAllActuators() {return actuatorService.getAllActuators();}

    @PutMapping
    public void updateActuator(@RequestBody @Valid Actuator actuator){
        actuatorService.updateActuator(actuator);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEnvironment(@PathVariable("id") Integer id) {
        actuatorService.deleteActuator(id);
    }
}
