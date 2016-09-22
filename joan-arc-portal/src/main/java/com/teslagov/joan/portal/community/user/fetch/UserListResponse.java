package com.teslagov.joan.portal.community.user.fetch;

import com.teslagov.joan.core.Response;
import com.teslagov.joan.core.UserResponseModel;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class UserListResponse extends Response
{
	public int total;

	public List<UserResponseModel> users;
}
