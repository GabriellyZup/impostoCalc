package com.impostoCalc.config.test;

import com.impostoCalc.config.SwaggerConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    @Test
    void testCustomOpenAPI() {
        // Arrange
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // Act
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        // Assert
        assertNotNull(openAPI);
        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Imposto Calc API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("API para cálculo de impostos", info.getDescription());
        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("Apache 2.0", license.getName());
        assertEquals("https://springdoc.org", license.getUrl());
    }
}