package com.floralstack.floralstackbackend.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class IdentityRequestModel {
    @NotNull
    private Integer id;

    public IdentityRequestModel(@JsonProperty("id") Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
