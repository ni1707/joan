package com.teslagov.joan.portal.group.userremove;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teslagov.joan.Response;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class GroupUserRemoveResponse extends Response
{
	public List<String> notRemoved;

	@JsonIgnore
	public boolean allUsersRemoved()
	{
		return notRemoved != null && !notRemoved.isEmpty();
	}
}
