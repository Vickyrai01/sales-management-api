package com.github.vickyrai01.salesmanagement.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Schema(description = "Validation errors by field.")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

    @Schema(
            description = "Map of field name to error message",
            example = "{\"name\":\"must not be blank\",\"price\":\"must be greater than 0\"}"
    )
    private Map<String, Object> errors;
}