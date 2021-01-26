package com.floralstack.floralstackbackend.sensor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.actuator.Actuator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class StaticSensor extends Sensor{
    @NotNull
    Double thresholdOffset;

    public StaticSensor(Integer id,
                        @JsonProperty("name") @NotBlank String name,
                        @JsonProperty("description") String description,
                        @JsonProperty("priority") @NotBlank String priority,
                        @JsonProperty("output_identifier") @NotBlank String outputIdentifier,
                        @JsonProperty("unit_of_measurement") @NotBlank String unitOfMeasurement,
                        @JsonProperty("last_measurement_value") Double lastMeasurementValue,
                        @JsonProperty("threshold_type") @NotBlank String thresholdType,
                        @JsonProperty("actuators") List<Actuator> actuators,
                        @JsonProperty("threshold_offset") @NotBlank Double thresholdOffset) {
        super(id, name, description, priority, outputIdentifier, unitOfMeasurement, lastMeasurementValue, thresholdType, actuators);
        this.thresholdOffset = thresholdOffset;
    }

    public Double getThresholdOffset() {
        return thresholdOffset;
    }

    @Override
    public String toString() {
        return "StaticSensor{" +
                "thresholdOffset=" + thresholdOffset +
                '}';
    }
}
