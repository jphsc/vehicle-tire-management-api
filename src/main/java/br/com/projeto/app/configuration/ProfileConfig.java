package br.com.projeto.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

@Configuration
public class ProfileConfig {

	private final Environment environment;
	
	public ProfileConfig(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void logProfileAtivo() {
        System.out.println("Profile ativo: " + String.join(", ", environment.getActiveProfiles()));
    }
}
