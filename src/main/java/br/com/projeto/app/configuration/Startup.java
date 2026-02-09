package br.com.projeto.app.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

	@Value("${spring.datasource.url}") 
	private String host;
	
	@Value("${spring.datasource.username}") 
	private String user;
	
	@Value("${spring.datasource.password}") 
	private String password;
	
	@Value("${spring.flyway.enabled}") 
	private Boolean flywayActive;

	@Override
	public void run(String... args) throws Exception {
		if(flywayActive)
			this.applyMigrations();
	}
	
	private void applyMigrations() {
		Flyway flyway = null;
		
		try {
			flyway = Flyway.configure().dataSource(host, user, password).load();
			flyway.migrate();
		} catch (Exception e) {
			e.printStackTrace();
			flyway.repair();
		}
	}

}
