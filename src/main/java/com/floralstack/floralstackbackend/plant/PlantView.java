package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.user.User;

public class PlantView {
    private Plant plant;
    private User owner;
    private Environment environment;

    public PlantView(@JsonProperty("plant") Plant plant,
                     @JsonProperty("owner") User owner,
                     @JsonProperty("environment") Environment environment) {
        this.plant = plant;
        this.owner = owner;
        this.environment = environment;
    }

    public Plant getPlant() {
        return plant;
    }

    public User getOwner() {
        return owner;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "PlantView{" +
                "plant=" + plant +
                ", owner=" + owner +
                ", environment=" + environment +
                '}';
    }
}
