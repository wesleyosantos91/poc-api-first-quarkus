package io.github.wesleyosantos91.swagger;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                description = "Prova de conseito API First openapitools e quarkus",
                title = "API First",
                version = "1.0.0-SNAPSHOT",
                contact = @Contact(name = "Wesley Oliveira Santos", email = "wesleyosantos91@gmail.com")
        )
)
public class ApplicationOpenApi extends Application {
}
