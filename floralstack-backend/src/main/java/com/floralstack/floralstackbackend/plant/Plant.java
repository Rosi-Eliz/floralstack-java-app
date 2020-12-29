package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Plant {
    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final Integer environmentID;
    private final Integer ownerID;

    public Plant(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("environment_ID") Integer environmentID,
                 @JsonProperty("owner_ID") Integer ownerID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.environmentID = environmentID;
        this.ownerID = ownerID;
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

    public Integer getEnvironmentID() {
        return environmentID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }


    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", environment_ID=" + environmentID +
                ", owner_ID=" + ownerID +
                '}';
    }
}
