package com.teslagov.joan.core;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class UserResponseModel extends Response {
	public String username;

	public String fullName;

	public String description;

	public String email;

	public List<String> groups;

	public String role;

	@Override
	public String toString() {
		return "UserResponseModel{" +
			"username='" + username + '\'' +
			", fullName='" + fullName + '\'' +
			", description='" + description + '\'' +
			", email='" + email + '\'' +
			", groups=" + groups +
			", role='" + role + '\'' +
			'}';
	}
}
