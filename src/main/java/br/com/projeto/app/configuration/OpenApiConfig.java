package br.com.projeto.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		
		Info info = new Info()
				.title("Tires Control API - Sistema de Veículos")
				.version("1.0.0")
				.description("Documentação da API do sistema Spring Boot");
		
		return new OpenAPI().info(info);
	}
}
