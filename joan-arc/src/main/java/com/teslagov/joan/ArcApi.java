package com.teslagov.joan;

import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.group.update.GroupUpdateResponse;
import com.teslagov.joan.portal.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.group.userremove.GroupUserRemoveResponse;
import com.teslagov.joan.server.user.UserAddResponse;
import org.apache.http.client.HttpClient;

import java.util.List;

/**
 * A facade for hitting both the Portal REST API and the ArcGIS Server REST API.
 *
 * @author Kevin Chen
 */
public class ArcApi
{
	private final ArcPortalApi arcPortalApi;

	private final ArcServerApi arcServerApi;

	public ArcApi( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.arcPortalApi = new ArcPortalApi( httpClient, arcConfiguration );
		this.arcServerApi = new ArcServerApi( httpClient, arcConfiguration );
	}

	public void getPortal()
	{
		arcPortalApi.getPortal();
	}

	public List<User> fetchUsers()
	{
		return arcPortalApi.fetchUsers();
	}

	public List<User> fetchUsers( int start, int num )
	{
		return arcPortalApi.fetchUsers( start, num );
	}

	public GroupCreateResponse createGroup( Group group )
	{
		return arcPortalApi.createGroup( group );
	}

	public GroupDeleteResponse deleteGroup( Group group )
	{
		return arcPortalApi.deleteGroup( group );
	}

	public GroupDeleteResponse deleteGroup( String groupID )
	{
		return arcPortalApi.deleteGroup( groupID );
	}

	public GroupUpdateResponse updateGroup( Group group )
	{
		return arcPortalApi.updateGroup( group );
	}

	public GroupUserAddResponse addUsersToGroup( Group group, List<String> usernames )
	{
		return arcPortalApi.addUsersToGroup( group, usernames );
	}

	public GroupUserRemoveResponse removeUsersFromGroup( Group group, List<String> usernames )
	{
		return arcPortalApi.removeUsersFromGroup( group, usernames );
	}

	public UserAddResponse addUser( User user )
	{
		return arcServerApi.addUser( user );
	}
}
