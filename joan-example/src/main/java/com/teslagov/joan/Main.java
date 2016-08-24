package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.http.HttpClientFactory;
import com.teslagov.joan.portal.portal.PortalResponse;
import com.teslagov.joan.portal.portal.PortalFetcher;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.joan.portal.token.PortalTokenResponse;
import com.teslagov.joan.portal.user.UserCreator;
import com.teslagov.joan.portal.user.UserFetcher;
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

		PortalTokenFetcher portalTokenFetcher = new PortalTokenFetcher();

		PortalTokenResponse portalTokenResponse = portalTokenFetcher.fetchToken( httpClient, arcConfiguration );

		logger.debug( "PortalTokenResponse successful: {}", portalTokenResponse.isSuccess() );
		logger.debug( "PortalTokenResponse toString: {}", portalTokenResponse );

		long expiresEpochMs = portalTokenResponse.getExpires();
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, ZoneOffset.UTC );
		logger.debug( "Current time = {}", LocalDateTime.now( ZoneOffset.UTC ) );
		logger.debug( "Token expires at {}", localDateTime );

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

		PortalFetcher portalFetcher = new PortalFetcher();
		PortalResponse portalResponse = portalFetcher.fetchPortal( httpClient, arcConfiguration, portalTokenResponse );
		logger.debug( "Portal ID = {}", portalResponse.id );

		UserFetcher userFetcher = new UserFetcher();
		userFetcher.fetchUsers( httpClient, arcConfiguration, portalTokenResponse, portalResponse );

		UserCreator userCreator = new UserCreator();
		userCreator.createArcGisUser(
			httpClient,
			arcConfiguration,
			portalTokenResponse,
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
