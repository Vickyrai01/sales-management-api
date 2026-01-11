package com.github.vickyrai01.salesmanagement.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Validation error response containing field-level error messages")
public class ValidationErrorResponse {
    
    @Schema(description = "Map of field names to their validation error messages", example = "{\"name\": \"Name cannot be blank\", \"price\": \"Price must be positive\"}")
    private Map<String, Object> errors;
}
