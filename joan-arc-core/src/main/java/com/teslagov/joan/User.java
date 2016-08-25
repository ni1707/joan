package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class User extends Response
{
	private final String username;

	private final String password;

	private final String fullname;

	private final String description;

	private final String email;

	public List<String> groups;

	public String role;

	private User( String username, String password, String fullname, String description, String email )
	{
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.description = description;
		this.email = email;
	}

	public static Builder newUser( String username, String password )
	{
		return new Builder( username, password );
	}

	public static class Builder
	{
		private final String username;

		private final String password;

		private String fullname;

		private String description;

		private String email;

		public Builder( String username, String password )
		{
			this.username = username;
			this.password = password;
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

		public Builder email( String email )
		{
			this.email = email;
			return this;
		}

		public User build()
		{
			return new User( username, password, fullname, description, email );
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
}
