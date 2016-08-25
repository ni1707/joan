package com.teslagov.joan.portal.user.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.core.UserResponseModel;

import java.util.List;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class UserListResponse
{
	public int total;

	public List<UserResponseModel> users;
}
