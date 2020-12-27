package com.floralstack.floralstackbackend.plant;

import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface PlantDataAccessServiceProvider {
    void addPlant(@RequestBody @Valid Plant plant);
}
