package com.floralstack.floralstackbackend.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.actuator.Actuator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CalibratedSensor extends Sensor{
    @NotNull
    Double maxValue;
    @NotNull
    Double minValue;
    @NotNull
    Double percentageThreshold;

    public CalibratedSensor(@JsonProperty("id") Integer id,
                            @JsonProperty("name") @NotBlank String name,
                            @JsonProperty("description") String description,
                            @JsonProperty("priority") @NotBlank String priority,
                            @JsonProperty("output_identifier") @NotBlank String outputIdentifier,
                            @JsonProperty("unit_of_measurement") @NotBlank String unitOfMeasurement,
                            @JsonProperty("last_measurement_value") Double lastMeasurementValue,
                            @JsonProperty("threshold_type") @NotBlank String thresholdType,
                            @JsonProperty("actuators") List<Actuator> actuators,
                            @JsonProperty("max_value") @NotNull Double maxValue,
                            @JsonProperty("min_value") @NotNull Double minValue,
                            @JsonProperty("percentage_threshold") @NotNull Double percentageThreshold) {
        super(id, name, description, priority, outputIdentifier, unitOfMeasurement, lastMeasurementValue, thresholdType, actuators);
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.percentageThreshold = percentageThreshold;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getPercentageThreshold() {
        return percentageThreshold;
    }

    @Override
    public String toString() {
        return "CalibratedSensor{" +
                "maxValue=" + maxValue +
                ", minValue=" + minValue +
                ", percentageThreshold=" + percentageThreshold +
                '}';
    }
}
