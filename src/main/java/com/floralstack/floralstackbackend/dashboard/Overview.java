package com.floralstack.floralstackbackend.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Overview {
    private final Integer plantsCount;
    private final Integer usersCount;
    private final Integer environmentsCount;
    private final Integer staticSensorsCount;
    private final Integer calibratedSensorsCount;
    private final Integer actuatorsCount;

    public Overview(@JsonProperty("plants_count") Integer plantsCount,
                    @JsonProperty("users_count") Integer usersCount,
                    @JsonProperty("environments_count") Integer environmentsCount,
                    @JsonProperty("static_sensors_count") Integer staticSensorsCount,
                    @JsonProperty("calibrated_sensors_count") Integer calibratedSensorsCount,
                    @JsonProperty("actuators_count") Integer actuatorsCount) {
        this.plantsCount = plantsCount;
        this.usersCount = usersCount;
        this.environmentsCount = environmentsCount;
        this.staticSensorsCount = staticSensorsCount;
        this.calibratedSensorsCount = calibratedSensorsCount;
        this.actuatorsCount = actuatorsCount;
    }

    public Integer getPlantsCount() {
        return plantsCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public Integer getEnvironmentsCount() {
        return environmentsCount;
    }

    public Integer getStaticSensorsCount() {
        return staticSensorsCount;
    }

    public Integer getCalibratedSensorsCount() {
        return calibratedSensorsCount;
    }

    public Integer getActuatorsCount() {
        return actuatorsCount;
    }
}
