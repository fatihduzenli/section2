package com.eazybank.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Credit Cards microservice REST API Documentation",
				description = "EazyBank Credit Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Fatih DUZENLI",
						email = "fatih@eazybank.com",
						url = "https://www.eazybank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.eazybank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EazyBank Credit Card microservice REST API Documentation",
				url = "https://www.eazybytes.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
