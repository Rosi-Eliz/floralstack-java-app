package com.floralstack.floralstackbackend.sensor;

/*CREATE TABLE sensor(
  id INTEGER UNIQUE,
  name VARCHAR2(30) NOT NULL,
  description VARCHAR2(30),
  priority VARCHAR2(30),
  output_identifier VARCHAR2(30) UNIQUE
  CHECK (REGEXP_LIKE(output_identifier,'(an([1-9][0-9]?|100)M([1-9][0-9]?|100)$)|(di([1-9][0-9]?|100)$)'),
  unit_of_measurement VARCHAR(30) NOT NULL,
  last_measurement_value NUMERIC(6,4),
  threshold_type VARCHAR(30),
  PRIMARY KEY (id),
);*/

import com.fasterxml.jackson.annotation.JsonProperty;
import com.floralstack.floralstackbackend.actuator.Actuator;

import javax.validation.constraints.NotBlank;

public class Sensor {
    private Integer id;
    @NotBlank
    private final String name;
    private final String description;
    @NotBlank
    private final String priority;
    @NotBlank
    private final String outputIdentifier;
    @NotBlank
    private final String unitOfMeasurement;
    private final Double lastMeasurementValue;
    @NotBlank
    private final String thresholdType;
    private Actuator actuator;

    public Sensor(Integer id,
                  String name,
                  String description,
                  String priority,
                  String outputIdentifier,
                  String unitOfMeasurement,
                  Double lastMeasurementValue,
                  String thresholdType,
                  Actuator actuator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.outputIdentifier = outputIdentifier;
        this.unitOfMeasurement = unitOfMeasurement;
        this.lastMeasurementValue = lastMeasurementValue;
        this.thresholdType = thresholdType;
        this.actuator = actuator;
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

    public String getPriority() {
        return priority;
    }

    public String getOutputIdentifier() {
        return outputIdentifier;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public Double getLastMeasurementValue() {
        return lastMeasurementValue;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public Actuator getActuator() {
        return actuator;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", outputIdentifier='" + outputIdentifier + '\'' +
                ", unitOfMeasurement='" + unitOfMeasurement + '\'' +
                ", lastMeasurementValue=" + lastMeasurementValue +
                ", thresholdType='" + thresholdType + '\'' +
                ", actuator=" + actuator +
                '}';
    }
}
