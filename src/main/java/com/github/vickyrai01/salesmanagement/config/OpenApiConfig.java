package com.github.vickyrai01.salesmanagement.config;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    public static final String SECURITY_SCHEME_BASIC = "basicAuth";

    @Bean
    public OpenAPI salesManagementOpenAPI() {

        var basicAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic");

        var errorMessageSchema = new Schema<>()
                .$ref("#/components/schemas/ErrorMessage");

        var components = new Components()
                .addSecuritySchemes(SECURITY_SCHEME_BASIC, basicAuth)
                .addResponses("401Unauthorized", new ApiResponse()
                        .description("Unauthorized - Authentication is required")
                        .content(new Content().addMediaType("application/json",
                                new MediaType().schema(errorMessageSchema))))
                .addResponses("403Forbidden", new ApiResponse()
                        .description("Forbidden - Insufficient permissions")
                        .content(new Content().addMediaType("application/json",
                                new MediaType().schema(errorMessageSchema))))
                .addResponses("500InternalServerError", new ApiResponse()
                        .description("Internal Server Error - Unexpected server error")
                        .content(new Content().addMediaType("application/json",
                                new MediaType().schema(errorMessageSchema))));

        return new OpenAPI()
                .info(new Info()
                        .title("Sales Management API")
                        .description("""
                                REST API for managing branches, stock, products, categories and sales.
                                
                                Security: HTTP Basic Auth with role-based access control.
                                """)
                        .version("1.0.0"))
                .components(components)
                .security(List.of(new SecurityRequirement().addList(SECURITY_SCHEME_BASIC)))
                .tags(List.of(
                        new Tag().name("Branches").description("Operations for managing branches"),
                        new Tag().name("Branch Stock").description("Operations for managing stock per branch (product x branch)"),
                        new Tag().name("Categories").description("Operations for managing product categories"),
                        new Tag().name("Products").description("Operations for managing products"),
                        new Tag().name("Sales").description("Operations for managing sales and state transitions")
                ));
    }
}