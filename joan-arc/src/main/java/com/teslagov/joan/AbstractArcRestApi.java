package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * @author Kevin Chen
 */
public abstract class AbstractArcRestApi
{
	private static final Logger logger = LoggerFactory.getLogger( AbstractArcRestApi.class );

	protected TokenResponse tokenResponse;

	protected final HttpClient httpClient;

	protected final ArcConfiguration arcConfiguration;

	protected final ZoneOffset zoneOffset;

	protected final TokenFetcher tokenFetcher;

	protected final String apiName;

	public AbstractArcRestApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenFetcher tokenFetcher,
		String apiName
	)
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
		this.zoneOffset = zoneOffset;
		this.tokenFetcher = tokenFetcher;
		this.apiName = apiName;
	}

	protected void fetchToken()
	{
		tokenResponse = tokenFetcher.fetchToken( httpClient, arcConfiguration );
		logger.debug( "TokenResponse successful: {}", tokenResponse.isSuccess() );
		logger.debug( "TokenResponse toString: {}", tokenResponse );

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

	protected void refreshTokenIfNecessary()
	{
		if ( isTokenExpired() )
		{
			fetchToken();
		}
	}

	protected boolean isTokenExpired()
	{
		if ( tokenResponse == null )
		{
			logger.debug( "{} token is null... fetching one now...", apiName );
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

	protected LocalDateTime getTokenExpirationTime()
	{
		long expiresEpochMs = tokenResponse.getExpires();
		return LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, zoneOffset );
	}
}
