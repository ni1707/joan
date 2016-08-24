package com.teslagov.joan.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class UserListResponse
{
	public int total;

	public List<User> users;
}
