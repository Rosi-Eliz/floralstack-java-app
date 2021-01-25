package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.environment.Environment;
import com.floralstack.floralstackbackend.sensor.CalibratedSensor;
import com.floralstack.floralstackbackend.sensor.StaticSensor;
import com.floralstack.floralstackbackend.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plant {
    public static class Create{
        @NotBlank
        private final String name;
        private final String description;
        private final Integer environmentId;
        private final Integer ownerId;

        public Create(
                      @JsonProperty("name") String name,
                      @JsonProperty("description") String description,
                      @JsonProperty("environment_id") Integer environmentId,
                      @JsonProperty("owner_id") Integer ownerId) {
            this.name = name;
            this.description = description;
            this.environmentId = environmentId;
            this.ownerId = ownerId;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Integer getEnvironmentId() {
            return environmentId;
        }

        public Integer getOwnerId() {
            return ownerId;
        }
    }

    public static class Update{
        @NotNull
        private final Integer id;
        @NotBlank
        private final String name;
        private final String description;
        private final Integer environmentId;
        private final Integer ownerId;

        public Update(@JsonProperty("id") Integer id,
                      @JsonProperty("name") String name,
                      @JsonProperty("description") String description,
                      @JsonProperty("environment_id") Integer environmentId,
                      @JsonProperty("owner_id") Integer ownerId) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.environmentId = environmentId;
            this.ownerId = ownerId;
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

        public Integer getEnvironmentId() {
            return environmentId;
        }

        public Integer getOwnerId() {
            return ownerId;
        }
    }

    private final Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final Environment environment;
    private final User owner;
    private List<StaticSensor> staticSensorsList;
    private List<CalibratedSensor> calibratedSensorsList;
    private final Date creationDate;

    public Plant(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("environment") Environment environment,
                 @JsonProperty("owner") User owner,
                 @JsonProperty("static_sensors_list") List<StaticSensor> staticSensorsList,
                 @JsonProperty("calibrated_sensors_list") List<CalibratedSensor> calibratedSensorsList,
                 @JsonProperty("creation_date") Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.environment = environment;
        this.owner = owner;
        this.staticSensorsList = staticSensorsList;
        this.calibratedSensorsList = calibratedSensorsList;
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

    public List<CalibratedSensor> getCalibratedSensorsList() {
        return calibratedSensorsList;
    }

    public void setStaticSensorsList(List<StaticSensor> staticSensorsList)
    {
        this.staticSensorsList = new ArrayList<>(staticSensorsList);
    }
    public void setCalibratedSensorsList(List<CalibratedSensor> calibratedSensorsList)
    {
        this.calibratedSensorsList = new ArrayList<>(calibratedSensorsList);
    }
}
