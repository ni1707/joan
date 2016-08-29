package com.teslagov.joan.api.portal;

import com.teslagov.joan.api.AbstractArcRestApi;
import com.teslagov.joan.api.ArcApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenRefresher;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.core.UserResponseModel;
import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.group.create.GroupCreator;
import com.teslagov.joan.portal.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.group.delete.GroupDeleter;
import com.teslagov.joan.portal.group.update.GroupUpdateResponse;
import com.teslagov.joan.portal.group.update.GroupUpdater;
import com.teslagov.joan.portal.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.group.useradd.GroupUserAdder;
import com.teslagov.joan.portal.group.userremove.GroupUserRemoveResponse;
import com.teslagov.joan.portal.group.userremove.GroupUserRemover;
import com.teslagov.joan.portal.portal.PortalFetcher;
import com.teslagov.joan.portal.portal.PortalResponse;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.joan.portal.user.create.UserCreateResponse;
import com.teslagov.joan.portal.user.create.UserCreator;
import com.teslagov.joan.portal.user.fetch.UserFetcher;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.List;

/**
 * @author Kevin Chen
 */
public class ArcPortalApi extends AbstractArcRestApi
{
	private static final Logger logger = LoggerFactory.getLogger( ArcApi.class );

	private final PortalFetcher portalFetcher = new PortalFetcher();

	private PortalResponse portalResponse;

	private final UserFetcher userFetcher = new UserFetcher();

	private final GroupCreator groupCreator = new GroupCreator();

	private final GroupDeleter groupDeleter = new GroupDeleter();

	private final GroupUpdater groupUpdater = new GroupUpdater();

	private final GroupUserAdder groupUserAdder = new GroupUserAdder();

	private final GroupUserRemover groupUserRemover = new GroupUserRemover();

	private final UserCreator userCreator = new UserCreator();

	public ArcPortalApi( HttpClient httpClient, ArcConfiguration arcConfiguration, ZoneOffset zoneOffset )
	{
		super(
			httpClient,
			arcConfiguration,
			zoneOffset,
			new TokenRefresher(
				new PortalTokenFetcher(
					httpClient,
					arcConfiguration
				),
				zoneOffset
			),
			"Portal"
		);
	}

	public void getPortal()
	{
		refreshTokenIfNecessary();
		portalResponse = portalFetcher.fetchPortal( httpClient, arcConfiguration, tokenResponse );
		logger.debug( "Portal ID = {}", portalResponse.id );
	}

	public List<UserResponseModel> fetchUsers()
	{
		return fetchUsers( 0, 100 );
	}

	public List<UserResponseModel> fetchUsers( int start, int num )
	{
		refreshTokenIfNecessary();

		if ( portalResponse == null )
		{
			getPortal();
		}

		return userFetcher.fetchUsers( httpClient, arcConfiguration, tokenResponse, portalResponse, start, num );
	}

	public GroupCreateResponse createGroup( Group group )
	{
		refreshTokenIfNecessary();
		GroupCreateResponse groupCreateResponse = groupCreator.createGroup( httpClient, arcConfiguration, tokenResponse, group );
		logger.debug( "GROUP ACCESS = {}", groupCreateResponse.group.access );

		return groupCreateResponse;
	}

	public GroupDeleteResponse deleteGroup( Group group )
	{
		return deleteGroup( group.id );
	}

	public GroupDeleteResponse deleteGroup( String groupID )
	{
		refreshTokenIfNecessary();
		return groupDeleter.deleteGroup( httpClient, arcConfiguration, tokenResponse, groupID );
	}

	public GroupUpdateResponse updateGroup( Group group )
	{
		refreshTokenIfNecessary();
		return groupUpdater.updateGroup( httpClient, arcConfiguration, tokenResponse, group );
	}

	public GroupUserAddResponse addUsersToGroup( Group group, List<String> usernames )
	{
		refreshTokenIfNecessary();
		return groupUserAdder.addUserToGroup( httpClient, arcConfiguration, tokenResponse, group, usernames );
	}

	public GroupUserRemoveResponse removeUsersFromGroup( Group group, List<String> usernames )
	{
		refreshTokenIfNecessary();
		return groupUserRemover.removeUsersFromGroup( httpClient, arcConfiguration, tokenResponse, group, usernames );
	}

	public UserCreateResponse addUser( UserRequestModel userRequestModel )
	{
		refreshTokenIfNecessary();
		return userCreator.createUser( httpClient, arcConfiguration, tokenResponse, userRequestModel );
	}
}
