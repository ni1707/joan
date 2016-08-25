package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.server.token.ServerTokenFetcher;
import com.teslagov.joan.server.token.ServerTokenResponse;
import com.teslagov.joan.server.user.UserAddResponse;
import com.teslagov.joan.server.user.UserAdder;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * @author Kevin Chen
 */
public class ArcServerApi extends AbstractArcRestApi
{
	private static final Logger logger = LoggerFactory.getLogger( ArcServerApi.class );

	private final HttpClient httpClient;

	private final ArcConfiguration arcConfiguration;

	private final ZoneOffset zoneOffset;

	private final ServerTokenFetcher serverTokenFetcher = new ServerTokenFetcher();

	private final UserAdder userAdder = new UserAdder();

	public ArcServerApi( HttpClient httpClient, ArcConfiguration arcConfiguration, ZoneOffset zoneOffset )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
		this.zoneOffset = zoneOffset;
	}

	public UserAddResponse addUser( User user )
	{
		return userAdder.addUser( httpClient, arcConfiguration, tokenResponse, user );
	}

	public void fetchToken()
	{
		tokenResponse = serverTokenFetcher.fetchServerToken( httpClient, arcConfiguration );
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
