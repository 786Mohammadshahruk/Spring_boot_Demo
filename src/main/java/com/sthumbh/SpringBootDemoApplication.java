package com.sthumbh;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@OpenAPIDefinition(
		info = @Info(
				title = "Employee Management System",
				description = "Spring Boot Rest APi Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "XYZ NAME",
						email = "abc@gmail.com",
						url = "https.gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https.gmail.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Rest APi Employee Management System",
				url = "https.gmail.com"
		)
)
@EnableScheduling
@EnableAsync
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
