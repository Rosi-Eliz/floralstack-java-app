package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.sensor.StaticSensor;
import com.floralstack.floralstackbackend.user.User;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plant {
    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final Environment environment;
    private final User owner;
    private List<StaticSensor> staticSensorsList;
    private final Date creationDate;

    public Plant(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 Environment environment,
                 User owner,
                 List<StaticSensor> staticSensorsList,
                 @JsonProperty("creation_date") Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.environment = environment;
        this.owner = owner;
        this.staticSensorsList = staticSensorsList;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public User getOwner() {
        return owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<StaticSensor> getStaticSensorsList() {
        return staticSensorsList;
    }

    public void setStaticSensorsList(List<StaticSensor> staticSensorsList)
    {
        this.staticSensorsList = new ArrayList<>(staticSensorsList);
    }
}
