package com.teslagov.joan.api;

import com.teslagov.joan.api.portal.ArcPortalApi;
import com.teslagov.joan.api.server.ArcServerApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import com.teslagov.joan.core.TokenRefresher;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.portal.admin.security.user.delete.UserDeleteResponse;
import com.teslagov.joan.portal.content.delete.DeleteItemResponse;
import com.teslagov.joan.portal.content.publish.PublishItemResponse;
import com.teslagov.joan.portal.content.upload.UploadItemResponse;
import com.teslagov.joan.portal.models.PublishItemModel;
import com.teslagov.joan.portal.models.UploadItemModel;
import com.teslagov.joan.portal.sharing.community.group.Group;
import com.teslagov.joan.portal.sharing.community.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.sharing.community.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.sharing.community.group.update.GroupUpdateResponse;
import com.teslagov.joan.portal.sharing.community.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.sharing.community.group.userremove.GroupUserRemoveResponse;
import com.teslagov.joan.portal.sharing.token.PortalTokenFetcher;
import com.teslagov.joan.portal.admin.security.user.create.UserCreateResponse;
import com.teslagov.joan.portal.sharing.user.fetch.UserListResponse;
import com.teslagov.joan.server.token.ServerTokenFetcher;
import com.teslagov.joan.server.user.add.UserAddResponse;
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
		this.arcPortalApi = new ArcPortalApi(
			httpClient,
			arcConfiguration,
			ZONE_OFFSET,
			new TokenManager(
				new TokenRefresher(
					new PortalTokenFetcher(
						httpClient,
						arcConfiguration
					),
					ZONE_OFFSET
				)
			)
		);

		this.arcServerApi = new ArcServerApi(
			httpClient,
			arcConfiguration,
			ZONE_OFFSET,
			new TokenManager(
				new TokenRefresher(
					new ServerTokenFetcher(
						httpClient,
						arcConfiguration
					),
					ZONE_OFFSET
				)
			)
		);
	}

	public void getPortal()
	{
		arcPortalApi.getPortal();
	}

	public UserListResponse fetchUsers()
	{
		return arcPortalApi.fetchUsers();
	}

	public UserListResponse fetchUsers( int start, int num )
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

	public UserCreateResponse addUserViaPortal( UserRequestModel userRequestModel )
	{
		return arcPortalApi.addUser( userRequestModel );
	}

	public UserAddResponse addUserViaServer( UserRequestModel userRequestModel )
	{
		return arcServerApi.addUser( userRequestModel );
	}

	public UserDeleteResponse removeUser( String username )
	{
		return arcPortalApi.deleteUser( username );
	}

	public UploadItemResponse uploadItem(UploadItemModel uploadItemModel, String username )
	{
		return arcPortalApi.uploadItem(uploadItemModel, username );
	}

	public PublishItemResponse publishItem(PublishItemModel publishItemModel, String username )
	{
		return arcPortalApi.publishItem(publishItemModel, username);
	}

	public DeleteItemResponse deleteItem(String username, String id)
	{
		return arcPortalApi.deleteItem(username, id);
	}
}
