package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.User;
import com.teslagov.joan.core.UserResponseModel;
import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.group.update.GroupUpdateResponse;
import com.teslagov.joan.portal.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.group.userremove.GroupUserRemoveResponse;
import com.teslagov.joan.server.user.add.UserAddResponse;
import com.teslagov.joan.server.user.remove.UserRemoveResponse;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;
import java.util.List;

/**
 * A facade for hitting both the Portal REST API and the ArcGIS Server REST API.
 *
 * @author Kevin Chen
 */
public class ArcApi
{
	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

	private final ArcPortalApi arcPortalApi;

	private final ArcServerApi arcServerApi;

	public ArcApi( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.arcPortalApi = new ArcPortalApi( httpClient, arcConfiguration, ZONE_OFFSET );
		this.arcServerApi = new ArcServerApi( httpClient, arcConfiguration, ZONE_OFFSET );
	}

	public void getPortal()
	{
		arcPortalApi.getPortal();
	}

	public List<UserResponseModel> fetchUsers()
	{
		return arcPortalApi.fetchUsers();
	}

	public List<UserResponseModel> fetchUsers( int start, int num )
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

	public com.teslagov.joan.portal.user.add.UserAddResponse addUserViaPortal( User user )
	{
		return arcPortalApi.addUser( user );
	}

	public UserAddResponse addUserViaServer( User user )
	{
		return arcServerApi.addUser( user );
	}

	public UserRemoveResponse removeUser( String username )
	{
		return arcServerApi.removeUser( username );
	}
}
