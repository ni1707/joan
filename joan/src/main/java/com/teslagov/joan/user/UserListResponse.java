package com.teslagov.joan.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class UserListResponse
{
	public List<User> users;
}
