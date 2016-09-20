package com.teslagov.joan.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

	private final String accountId;

	private final String provider;

	private UserRequestModel(
		String username,
		String password,
		String fullname,
		String description,
		String email,
		String role,
		String accountId,
		String provider
	)
	{
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.description = description;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.accountId = accountId;
	}

	public static Builder newUser( String username, String password, String email, Role role, String accountId,
								   String provider, String description, String fullname )
	{
		return new Builder( username, password, email, role, accountId, provider, description, fullname );
	}

	public static class Builder
	{
		private final String username;

		private final String password;

		private final String email;

		private final Role role;

		private String fullname;

		private String description;

		private String accountId;

		private String provider;

		public Builder(
			String username,
			String password,
			String email,
			Role role,
			String accountId,
			String provider,
			String description,
			String fullname
		)
		{
			this.username = username;
			this.password = password;
			this.fullname = fullname;
			this.description = description;
			this.email = email;
			this.role = role;
			this.provider = provider;
			this.accountId = accountId;
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
			return new UserRequestModel( username, password, fullname, description, email, role.getName(), accountId,
					provider);
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
