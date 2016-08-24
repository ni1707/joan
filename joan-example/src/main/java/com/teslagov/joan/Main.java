package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teslagov.joan.token.TokenFetcher;
import com.teslagov.joan.token.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Chen
 */
public class Main
{
	private static final Logger logger = LoggerFactory.getLogger( Main.class );

	public static void main( String[] args )
	{
		ArcConfiguration arcConfiguration = ArcConfigurationFactory.createArcConfiguration();
		TokenFetcher tokenFetcher = new TokenFetcher();
		TokenResponse tokenResponse = tokenFetcher.fetchToken( arcConfiguration );

		logger.info( "TOKEN TOSTRING = {}", tokenResponse );

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
	}
}
