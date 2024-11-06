package org.jsp.events.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI usersMicroserviceOpenAPI() {

		Server localhost = new Server();
		localhost.setUrl("http://localhost:8080/events");
		localhost.setDescription("Development environment");
		Contact contact = new Contact();
		contact.setEmail("abc@gmail.com");
		contact.setName("Event Management");
		contact.setUrl("http://localhost:8080/events");
		License mitLicense = new License().name("MIT License").url("https://localhost:8080");
		Info info = new Info().title("Event Management").version("1.0").contact(contact)
				.description("This API exposes endpoints to manage Application.")
				.termsOfService("http://localhost:8080/events").license(mitLicense);
		return new OpenAPI().info(info).servers(List.of(localhost));
	}

}
