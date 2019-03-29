package com.keycloak.accountservice;

import org.keycloak.admin.client.Keycloak;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountserviceApplication.class, args);
	}

	@Bean
	Keycloak initKeycloakWithAdminRole() {
		return Keycloak.getInstance(
				"http://localhost:8083/auth",
				"master",
				"admin",
				"admin",
				"admin-cli");
	}

}
