package com.teslagov.joan.portal.sharing.community.group.useradd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teslagov.joan.core.Response;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class GroupUserAddResponse extends Response
{
	public List<String> notAdded;

	@JsonIgnore
	public boolean allUsersAdded()
	{
		return notAdded != null && !notAdded.isEmpty();
	}
}
