package com.floralstack.floralstackbackend.sensor;

import com.floralstack.floralstackbackend.commons.IdentityRequestModel;
import com.floralstack.floralstackbackend.user.User;
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

    //Static sensor methods
    @PostMapping("static")
    void createStaticSensor(@RequestBody @Valid StaticSensor staticSensor){
        sensorService.createStaticSensor(staticSensor);
    }
    @GetMapping("static")
    List<StaticSensor> getAllStaticSensors(){
        return sensorService.getAllStaticSensors();
    }

    @GetMapping("static-unattached")
    List<StaticSensor> getAllStaticUnattachedSensors(){
        return sensorService.getAllStaticUnattachedSensors();
    }

    @GetMapping("static/{id}")
    StaticSensor getStaticSensor(@PathVariable("id") Integer id) {
        return sensorService.getStaticSensor(id);
    }

    //Calibrated sensor methods
    @PostMapping("calibrated")
    void createCalibratedSensor(@RequestBody @Valid CalibratedSensor calibratedSensor){
        sensorService.createCalibratedSensor(calibratedSensor);
    }
    @GetMapping("calibrated")
    List<CalibratedSensor> getAllCalibratedSensors(){
        return sensorService.getAllCalibratedSensors();
    }

    @GetMapping("calibrated-unattached")
    List<CalibratedSensor> getAllCalibratedUnattachedSensors(){
        return sensorService.getAllCalibratedUnattachedSensors();
    }

    @GetMapping("calibrated/{id}")
    CalibratedSensor getCalibratedSensor(@PathVariable("id") Integer id) {
        return sensorService.getCalibratedSensor(id);
    }
    //Common methods
    @PutMapping("static")
    public void updateSensor(@RequestBody @Valid StaticSensor sensor){
        sensorService.updateSensor(sensor);
    }

    @PutMapping("calibrated")
    public void updateSensor(@RequestBody @Valid CalibratedSensor sensor){
        sensorService.updateSensor(sensor);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSensor(@PathVariable("id") Integer id) {
        sensorService.deleteSensor(id);
    }


    @PostMapping(path = "{id}/attach_actuator")
    public void attachActuator(@PathVariable("id") Integer id, @RequestBody @Valid IdentityRequestModel identityRequestModel){
        sensorService.attachActuator(id, identityRequestModel.getId());
    }
}
