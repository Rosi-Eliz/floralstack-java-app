package com.floralstack.floralstackbackend.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/sensors")
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("static")
    void createStaticSensor(@RequestBody @Valid StaticSensor staticSensor){
        sensorService.createStaticSensor(staticSensor);
    }

    @GetMapping("static")
    List<StaticSensor> getAllStaticSensors(){
        return sensorService.getAllStaticSensors();
    }
}
