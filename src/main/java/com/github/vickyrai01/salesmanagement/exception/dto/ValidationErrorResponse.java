package com.github.vickyrai01.salesmanagement.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(description = "Validation errors by field.")
public class ValidationErrorResponse {

    @Schema(
            description = "Map of field name to error message",
            example = "{\"name\":\"must not be blank\",\"price\":\"must be greater than 0\"}"
    )
    private Map<String, Object> errors;

    public ValidationErrorResponse() {}

    public ValidationErrorResponse(Map<String, Object> errors) {
        this.errors = errors;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }
}