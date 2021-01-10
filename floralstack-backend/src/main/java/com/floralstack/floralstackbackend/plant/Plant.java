package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Plant {
    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final Environment environment;
    private final User owner;
    private final Date creationDate;

    public Plant(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 Environment environment,
                 User owner,
                 @JsonProperty("creation_date") Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.environment = environment;
        this.owner = owner;
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
}
