package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.teslagov.joan.portal.user.UserFetcher;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Kevin Chen
 */
public class ArcPortalApi extends AbstractArcRestApi
{
	private static final Logger logger = LoggerFactory.getLogger( ArcApi.class );

	private final HttpClient httpClient;

	private final ArcConfiguration arcConfiguration;

	private final ZoneOffset zoneOffset;

	private final PortalTokenFetcher portalTokenFetcher = new PortalTokenFetcher();

	private final PortalFetcher portalFetcher = new PortalFetcher();

	private PortalResponse portalResponse;

	private final UserFetcher userFetcher = new UserFetcher();

	private final GroupCreator groupCreator = new GroupCreator();

	private final GroupDeleter groupDeleter = new GroupDeleter();

	private final GroupUpdater groupUpdater = new GroupUpdater();

	private final GroupUserAdder groupUserAdder = new GroupUserAdder();

	private final GroupUserRemover groupUserRemover = new GroupUserRemover();

	public ArcPortalApi( HttpClient httpClient, ArcConfiguration arcConfiguration, ZoneOffset zoneOffset )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
		this.zoneOffset = zoneOffset;
	}

	public void fetchToken()
	{
		tokenResponse = portalTokenFetcher.fetchToken( httpClient, arcConfiguration );
		logger.debug( "PortalTokenResponse successful: {}", tokenResponse.isSuccess() );
		logger.debug( "PortalTokenResponse toString: {}", tokenResponse );

		LocalDateTime expirationDate = getTokenExpirationTime();
		LocalDateTime now = LocalDateTime.now( zoneOffset );
		logger.debug( "Current time is {}", now );
		logger.debug( "Token will expire on {}", expirationDate );
		logger.debug(
			"Token expires in {} seconds ({} minutes)",
			now.until( expirationDate, ChronoUnit.SECONDS ),
			now.until( expirationDate, ChronoUnit.MINUTES )
		);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

		try
		{
			logger.debug( "TOKEN SERIALIZED = {}", objectMapper.writeValueAsString( tokenResponse ) );
		}
		catch ( JsonProcessingException e )
		{
			e.printStackTrace();
		}
	}

	public void getPortal()
	{
		refreshPortalTokenIfNecessary();
		portalResponse = portalFetcher.fetchPortal( httpClient, arcConfiguration, tokenResponse );
		logger.debug( "Portal ID = {}", portalResponse.id );
	}

	public List<User> fetchUsers()
	{
		return fetchUsers( 0, 100 );
	}

	public List<User> fetchUsers( int start, int num )
	{
		refreshPortalTokenIfNecessary();

		if ( portalResponse == null )
		{
			getPortal();
		}

		return userFetcher.fetchUsers( httpClient, arcConfiguration, tokenResponse, portalResponse, start, num );
	}

	public GroupCreateResponse createGroup( Group group )
	{
		refreshPortalTokenIfNecessary();
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
		refreshPortalTokenIfNecessary();
		return groupDeleter.deleteGroup( httpClient, arcConfiguration, tokenResponse, groupID );
	}

	public GroupUpdateResponse updateGroup( Group group )
	{
		refreshPortalTokenIfNecessary();
		return groupUpdater.updateGroup( httpClient, arcConfiguration, tokenResponse, group );
	}

	public GroupUserAddResponse addUsersToGroup( Group group, List<String> usernames )
	{
		refreshPortalTokenIfNecessary();
		return groupUserAdder.addUserToGroup( httpClient, arcConfiguration, tokenResponse, group, usernames );
	}

	public GroupUserRemoveResponse removeUsersFromGroup( Group group, List<String> usernames )
	{
		refreshPortalTokenIfNecessary();
		return groupUserRemover.removeUsersFromGroup( httpClient, arcConfiguration, tokenResponse, group, usernames );
	}

	// TODO maybe move this to a decorator class
	private void refreshPortalTokenIfNecessary()
	{
		if ( isTokenExpired() )
		{
			fetchToken();
		}
	}

	private boolean isTokenExpired()
	{
		if ( tokenResponse == null )
		{
			logger.debug( "Portal token is null... fetching one now..." );
			return true;
		}

		LocalDateTime now = LocalDateTime.now( zoneOffset );
		LocalDateTime expirationTime = getTokenExpirationTime();

		if ( now.isAfter( expirationTime ) )
		{
			logger.debug( "Current time {} is after token expiration time {}", now, expirationTime );
			return true;
		}

		return false;
	}

	private LocalDateTime getTokenExpirationTime()
	{
		long expiresEpochMs = tokenResponse.getExpires();
		return LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, zoneOffset );
	}
}
