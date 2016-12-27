package com.teslagov.joan.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author joncrain
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAdminRequestModel {
	private String username;
	private String firstname;
	private String lastname;
	private String role;
	private String email;
	private String provider;
	private String f;

	public UserAdminRequestModel() {}

	public UserAdminRequestModel username(String username) {
		this.username = username;
		return this;
	}

	public UserAdminRequestModel firstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public UserAdminRequestModel lastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public UserAdminRequestModel role(String role) {
		this.role = role;
		return this;
	}

	public UserAdminRequestModel email(String email) {
		this.email = email;
		return this;
	}

	public UserAdminRequestModel provider(String provider) {
		this.provider = provider;
		return this;
	}

	public UserAdminRequestModel f(String f) {
		this.f = f;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public String getProvider() {
		return provider;
	}

	public String getF() {
		return f;
	}

	@Override
	public String toString() {
		return "UserAdminRequestModel{" +
			"username='" + username + '\'' +
			", firstname='" + firstname + '\'' +
			", lastname='" + lastname + '\'' +
			", role='" + role + '\'' +
			", email='" + email + '\'' +
			", provider='" + provider + '\'' +
			", f='" + f + '\'' +
			'}';
	}
}
