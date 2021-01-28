package com.floralstack.floralstackbackend.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class PlantsBatch {
    private List<Plant> plants;
    private Integer currentPage;
    private Integer lastPage;
    private Integer batchSize;
    private Integer plantsCount;

    public PlantsBatch(@JsonProperty("plants") List<Plant> plants,
                       @JsonProperty("current_page") Integer currentPage,
                       @JsonProperty("last_page") Integer lastPage,
                       @JsonProperty("batch_size") Integer batchSize,
                       @JsonProperty("plants_count") Integer plantsCount) {
        this.plants = plants;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.batchSize = batchSize;
        this.plantsCount = plantsCount;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public Integer getPlantsCount() {
        return plantsCount;
    }
}
