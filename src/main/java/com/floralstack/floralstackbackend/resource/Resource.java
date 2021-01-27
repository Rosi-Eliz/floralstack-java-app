package com.floralstack.floralstackbackend.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Resource {

    private final Integer id;
    @NotNull
    private final Integer sensorId;
    private final String name;
    private final String description;
    private final String unitOfMeasurement;
    private final Double currentAmount;
    private final Double drawnAmount;

    public Resource(@JsonProperty("id")Integer id,
                    @JsonProperty("sensor_id")Integer sensorId,
                    @JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("unit_of_measurement") String unitOfMeasurement,
                    @JsonProperty("current_amount") Double currentAmount,
                    @JsonProperty("drawn_amount") Double drawnAmount) {
        this.id = id;
        this.sensorId = sensorId;
        this.name = name;
        this.description = description;
        this.unitOfMeasurement = unitOfMeasurement;
        this.currentAmount = currentAmount;
        this.drawnAmount = drawnAmount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public Double getDrawnAmount() {
        return drawnAmount;
    }
}
