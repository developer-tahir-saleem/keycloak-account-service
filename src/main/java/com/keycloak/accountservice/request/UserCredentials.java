package com.keycloak.accountservice.request;

import com.keycloak.accountservice.model.Device;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCredentials {

	@NotNull
	@Size( message = "Password is required !")
	private String password;

	@NotNull
	@Size(message = "Email is required !")
	private String email;

	private Device device;

}
