package com.embl.person.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class APIErrorResponse {

    @JsonProperty("errors")
    private List<APIError> errors;

    public APIErrorResponse() {
        errors = new ArrayList<>();
    }

    public void add(APIError error) {
        this.errors.add(error);
    }

    public List<APIError> getErrors() {
        return errors;
    }
}
