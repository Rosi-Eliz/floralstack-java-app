package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Plant {
    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final Integer environment_ID;
    private final Integer owner_ID;
    private final Integer action_record_ID;

    public Plant(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("environment_ID") Integer environment_ID,
                 @JsonProperty("owner_ID") Integer owner_ID,
                 @JsonProperty("action_record_ID") Integer action_record_ID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.environment_ID = environment_ID;
        this.owner_ID = owner_ID;
        this.action_record_ID = action_record_ID;
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

    public Integer getEnvironment_ID() {
        return environment_ID;
    }

    public Integer getOwner_ID() {
        return owner_ID;
    }

    public Integer getAction_record_ID() {
        return action_record_ID;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", environment_ID=" + environment_ID +
                ", owner_ID=" + owner_ID +
                ", action_record_ID=" + action_record_ID +
                '}';
    }

}
