package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.http.HttpClientFactory;
import com.teslagov.joan.portal.Portal;
import com.teslagov.joan.portal.PortalFetcher;
import com.teslagov.joan.token.TokenFetcher;
import com.teslagov.joan.token.TokenResponse;
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

		logger.info( "TokenResponse successful: {}", tokenResponse.isSuccess() );
		logger.info( "TokenResponse toString: {}", tokenResponse );

		long expiresEpochMs = tokenResponse.getExpires();
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond( expiresEpochMs / 1000, 0, ZoneOffset.UTC );
		logger.info( "Current time = {}", LocalDateTime.now( ZoneOffset.UTC ) );
		logger.info( "Token expires at {}", localDateTime );

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

		try
		{
			logger.info( "TOKEN SERIALIZED = {}", objectMapper.writeValueAsString( tokenResponse ) );
		}
		catch ( JsonProcessingException e )
		{
			e.printStackTrace();
		}

		PortalFetcher portalFetcher = new PortalFetcher();
		Portal portal = portalFetcher.fetchPortal( httpClient, arcConfiguration, tokenResponse );
		logger.info( "Portal ID = {}", portal.id );
	}
}
