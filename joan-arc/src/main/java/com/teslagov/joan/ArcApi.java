package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.GroupCreator;
import com.teslagov.joan.portal.group.GroupResponse;
import com.teslagov.joan.portal.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.group.delete.GroupDeleter;
import com.teslagov.joan.portal.portal.PortalFetcher;
import com.teslagov.joan.portal.portal.PortalResponse;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.joan.portal.token.PortalTokenResponse;
import com.teslagov.joan.portal.user.User;
import com.teslagov.joan.portal.user.UserFetcher;
import com.teslagov.joan.server.token.ServerTokenFetcher;
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
public class ArcApi
{
	private static final Logger logger = LoggerFactory.getLogger( ArcApi.class );

	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

	private final HttpClient httpClient;

	private final ArcConfiguration arcConfiguration;

	private final PortalTokenFetcher portalTokenFetcher = new PortalTokenFetcher();

	private PortalTokenResponse portalTokenResponse;

	private final PortalFetcher portalFetcher = new PortalFetcher();

	private PortalResponse portalResponse;

	private final UserFetcher userFetcher = new UserFetcher();

	private final GroupCreator groupCreator = new GroupCreator();

	private final GroupDeleter groupDeleter = new GroupDeleter();

	private final ServerTokenFetcher serverTokenFetcher = new ServerTokenFetcher();

	public ArcApi( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
	}

	public void fetchToken()
	{
		portalTokenResponse = portalTokenFetcher.fetchToken( httpClient, arcConfiguration );
		logger.debug( "PortalTokenResponse successful: {}", portalTokenResponse.isSuccess() );
		logger.debug( "PortalTokenResponse toString: {}", portalTokenResponse );

		LocalDateTime expirationDate = getTokenExpirationTime();
		LocalDateTime now = LocalDateTime.now( ZONE_OFFSET );
		logger.debug( "Current time is {}", now );
		logger.debug( "Token will expire on {}", expirationDate );
		logger.debug( "Token expires in {} seconds", now.until( expirationDate, ChronoUnit.SECONDS ) );

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

		try
		{
			logger.debug( "TOKEN SERIALIZED = {}", objectMapper.writeValueAsString( portalTokenResponse ) );
		}
		catch ( JsonProcessingException e )
		{
			e.printStackTrace();
		}
	}

	public void getPortal()
	{
		refreshPortalTokenIfNecessary();
		portalResponse = portalFetcher.fetchPortal( httpClient, arcConfiguration, portalTokenResponse );
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

		return userFetcher.fetchUsers( httpClient, arcConfiguration, portalTokenResponse, portalResponse, start, num );
	}

	public GroupResponse createGroup( Group group )
	{
		refreshPortalTokenIfNecessary();
		GroupResponse groupResponse = groupCreator.createGroup( httpClient, arcConfiguration, portalTokenResponse, group );
		logger.debug( "GROUP ACCESS = {}", groupResponse.group.access );

		return groupResponse;
	}

	public GroupDeleteResponse deleteGroup( Group group )
	{
		refreshPortalTokenIfNecessary();
		return groupDeleter.deleteGroup( httpClient, arcConfiguration, portalTokenResponse, group );
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
		if ( portalTokenResponse == null )
		{
			logger.debug( "Portal token is null... fetching one now..." );
			return true;
		}

		LocalDateTime now = LocalDateTime.now( ZONE_OFFSET );
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
		long expiresEpochMs = portalTokenResponse.getExpires();
		return LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, ZONE_OFFSET );
	}
}
