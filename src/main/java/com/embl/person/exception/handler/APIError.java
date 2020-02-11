package com.embl.person.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class APIError {

    private String errorKey;

    private String errorMessage;

    public APIError(String errorKey, String errorMessage) {
        this.errorKey = errorKey;
        this.errorMessage = errorMessage;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof APIError)) return false;
        APIError apiError = (APIError) o;
        return Objects.equals(getErrorKey(), apiError.getErrorKey()) &&
                Objects.equals(getErrorMessage(), apiError.getErrorMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErrorKey(), getErrorMessage());
    }
}

