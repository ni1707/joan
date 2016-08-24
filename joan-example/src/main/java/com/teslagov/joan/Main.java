package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.http.HttpClientFactory;
import com.teslagov.joan.portal.PortalResponse;
import com.teslagov.joan.portal.PortalFetcher;
import com.teslagov.joan.token.TokenFetcher;
import com.teslagov.joan.token.TokenResponse;
import com.teslagov.joan.user.UserCreator;
import com.teslagov.joan.user.UserFetcher;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public class Main
{
	private static final Logger logger = LoggerFactory.getLogger( Main.class );

	public static void main( String[] args )
	{
		ArcConfiguration arcConfiguration = ArcConfigurationFactory.createArcConfiguration();

		HttpClient httpClient = HttpClientFactory.createVeryUnsafePortalHttpClient( arcConfiguration );

		TokenFetcher tokenFetcher = new TokenFetcher();

		TokenResponse tokenResponse = tokenFetcher.fetchToken( httpClient, arcConfiguration );

		logger.debug( "TokenResponse successful: {}", tokenResponse.isSuccess() );
		logger.debug( "TokenResponse toString: {}", tokenResponse );

		long expiresEpochMs = tokenResponse.getExpires();
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, ZoneOffset.UTC );
		logger.debug( "Current time = {}", LocalDateTime.now( ZoneOffset.UTC ) );
		logger.debug( "Token expires at {}", localDateTime );

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

		PortalFetcher portalFetcher = new PortalFetcher();
		PortalResponse portalResponse = portalFetcher.fetchPortal( httpClient, arcConfiguration, tokenResponse );
		logger.debug( "Portal ID = {}", portalResponse.id );

		UserFetcher userFetcher = new UserFetcher();
		userFetcher.fetchUsers( httpClient, arcConfiguration, tokenResponse, portalResponse );

		UserCreator userCreator = new UserCreator();
		userCreator.createArcGisUser(
			httpClient,
			arcConfiguration,
			tokenResponse,
			"Cool.Person1",
			"Password123!",
			"Cool",
			"Person",
			"Cool Person1",
			"cool.person1@gmail.com",
			"a cool person"
		);
	}
}
