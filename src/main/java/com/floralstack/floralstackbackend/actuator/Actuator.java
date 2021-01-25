package com.floralstack.floralstackbackend.actuator;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.sensor.Sensor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Actuator {
    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    @NotBlank
    private final String priority;
    @NotBlank
    private final String inputIdentifier;

    public Actuator( @JsonProperty("id")Integer id,
                     @JsonProperty("name") String name,
                     @JsonProperty("description") String description,
                     @JsonProperty("priority") String priority,
                     @JsonProperty("input_identifier") String inputIdentifier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.inputIdentifier = inputIdentifier;
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

    public String getPriority() {
        return priority;
    }

    public String getInputIdentifier() {
        return inputIdentifier;
    }

    @Override
    public String toString() {
        return "Actuator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", inputIdentifier='" + inputIdentifier + '\'' +
                '}';
    }
}
