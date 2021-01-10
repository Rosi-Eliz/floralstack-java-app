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

    public Sensor(Integer id,
                  @NotBlank String name,
                  String description,
                  @NotBlank String priority,
                  @NotBlank String outputIdentifier,
                  @NotBlank String unitOfMeasurement,
                  Double lastMeasurementValue,
                  @NotBlank String thresholdType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.outputIdentifier = outputIdentifier;
        this.unitOfMeasurement = unitOfMeasurement;
        this.lastMeasurementValue = lastMeasurementValue;
        this.thresholdType = thresholdType;
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

    public void setId(Integer id) {
        this.id = id;
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
                '}';
    }

}
