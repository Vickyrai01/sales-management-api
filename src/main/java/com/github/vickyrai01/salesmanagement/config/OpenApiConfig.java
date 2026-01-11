package com.github.vickyrai01.salesmanagement.config;

import com.github.vickyrai01.salesmanagement.exception.dto.ErrorMessage;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sales Management API")
                        .description("REST API for managing branches, products, categories, branch stock, and sales")
                        .version("1.0.0"))
                .components(new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .description("HTTP Basic Authentication")))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .tags(Arrays.asList(
                        new Tag().name("Branches").description("Branch management operations"),
                        new Tag().name("Products").description("Product management operations"),
                        new Tag().name("Categories").description("Category management operations"),
                        new Tag().name("Branch Stock").description("Branch stock management operations"),
                        new Tag().name("Sales").description("Sales management operations")
                ));
    }

    @Bean
    public OperationCustomizer globalResponseCustomizer() {
        return (operation, handlerMethod) -> {
            // Add common responses for all operations
            operation.getResponses().addApiResponse("401", new ApiResponse()
                    .description("Unauthorized - Authentication required")
                    .content(new Content()));
            
            operation.getResponses().addApiResponse("403", new ApiResponse()
                    .description("Forbidden - Insufficient permissions")
                    .content(new Content()));
            
            operation.getResponses().addApiResponse("500", new ApiResponse()
                    .description("Internal Server Error")
                    .content(new Content().addMediaType("application/json",
                            new MediaType().schema(new Schema<ErrorMessage>().$ref("#/components/schemas/ErrorMessage")))));
            
            return operation;
        };
    }
}
