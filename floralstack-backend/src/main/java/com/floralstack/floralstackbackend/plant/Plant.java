package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Plant {
    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final Integer environmentID;
    private final Integer ownerID;
    private final Date creationDate;

    public Plant(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("environment_id") Integer environmentID,
                 @JsonProperty("owner_id") Integer ownerID,
                 @JsonProperty("creation_date") Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.environmentID = environmentID;
        this.ownerID = ownerID;
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

    public Integer getEnvironmentID() {
        return environmentID;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", environment_id=" + environmentID +
                ", owner_id=" + ownerID +
                ", creation_date=" + creationDate +
                '}';
    }
}
