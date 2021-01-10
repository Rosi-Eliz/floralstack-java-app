package com.floralstack.floralstackbackend.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CalibratedSensor extends Sensor{
    @NotBlank
    Double maxValue;
    @NotBlank
    Double minValue;
    @NotBlank
    Double percentageThreshold;

    public CalibratedSensor(@JsonProperty("id") Integer id,
                            @JsonProperty("name") @NotBlank String name,
                            @JsonProperty("description") String description,
                            @JsonProperty("priority") @NotBlank String priority,
                            @JsonProperty("output_identifer") @NotBlank String outputIdentifier,
                            @JsonProperty("unit_of_measurement") @NotBlank String unitOfMeasurement,
                            @JsonProperty("last_measurement_value") Double lastMeasurementValue,
                            @JsonProperty("threshold_type") @NotBlank String thresholdType,
                            @JsonProperty("max_value") @NotBlank Double maxValue,
                            @JsonProperty("min_value") @NotBlank Double minValue,
                            @JsonProperty("percentage_threshold") @NotBlank Double percentageThreshold) {
        super(id, name, description, priority, outputIdentifier, unitOfMeasurement, lastMeasurementValue, thresholdType);
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
