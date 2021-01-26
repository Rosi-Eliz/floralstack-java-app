package com.floralstack.floralstackbackend.sensor;

public class CreateSensorResult {
    private Integer queryResult;
    private Integer createdSensorId;

    public CreateSensorResult(Integer queryResult, Integer createdSensorId) {
        this.queryResult = queryResult;
        this.createdSensorId = createdSensorId;
    }

    public Integer getQueryResult() {
        return queryResult;
    }

    public Integer getCreatedSensorId() {
        return createdSensorId;
    }
}