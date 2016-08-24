package com.teslagov.joan.portal.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class User
{
	public String fullName;

	public String firstName;

	public String lastName;

	public String email;

	public String username;

	public List<String> groups;

	public String role;
}
