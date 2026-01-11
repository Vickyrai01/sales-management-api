package com.github.vickyrai01.salesmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

        return new OpenAPI()
                .info(new Info()
                        .title("Sales Management API")
                        .description("""
                                REST API para gestión de sucursales, stock, productos, categorías y ventas.

                                Seguridad: HTTP Basic Auth + roles.
                                """)
                        .version("1.0.0"))
                .schemaRequirement(SECURITY_SCHEME_BASIC, basicAuth)
                .security(List.of(new SecurityRequirement().addList(SECURITY_SCHEME_BASIC)))
                .tags(List.of(
                        new Tag().name("Branches").description("Operaciones sobre sucursales"),
                        new Tag().name("Branch Stock").description("Stock por sucursal (producto x sucursal)"),
                        new Tag().name("Categories").description("Operaciones sobre categorías"),
                        new Tag().name("Products").description("Operaciones sobre productos"),
                        new Tag().name("Sales").description("Operaciones sobre ventas y cambios de estado")
                ));
    }
}