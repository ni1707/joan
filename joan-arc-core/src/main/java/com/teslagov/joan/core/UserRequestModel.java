package com.teslagov.joan.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class UserRequestModel
{
	private final String username;

	private final String password;

	private final String fullname;

	private final String description;

	private final String email;

	private final String role;

	private UserRequestModel(
		String username,
		String password,
		String fullname,
		String description,
		String email,
		String role
	)
	{
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.description = description;
		this.email = email;
		this.role = role;
	}

	public static Builder newUser( String username, String password, String email, Role role )
	{
		return new Builder( username, password, email, role );
	}

	public static class Builder
	{
		private final String username;

		private final String password;

		private final String email;

		private final Role role;

		private String fullname;

		private String description;

		public Builder(
			String username,
			String password,
			String email,
			Role role
		)
		{
			this.username = username;
			this.password = password;
			this.email = email;
			this.role = role;
		}

		public Builder fullname( String fullname )
		{
			this.fullname = fullname;
			return this;
		}

		public Builder description( String description )
		{
			this.description = description;
			return this;
		}

		public UserRequestModel build()
		{
			return new UserRequestModel( username, password, fullname, description, email, role.getName() );
		}
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getFullname()
	{
		return fullname;
	}

	public String getDescription()
	{
		return description;
	}

	public String getEmail()
	{
		return email;
	}

	public String getRole()
	{
		return role;
	}

	@Override
	public String toString()
	{
		return "UserRequestModel{" +
			"username='" + username + '\'' +
			", password='" + password + '\'' +
			", fullname='" + fullname + '\'' +
			", description='" + description + '\'' +
			", email='" + email + '\'' +
			", role=" + role +
			'}';
	}
}
