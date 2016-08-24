package com.teslagov.joan.token;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Endpoint;
import com.teslagov.joan.http.HttpClientFactory;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Chen
 */
public class TokenFetcher
{
	private static final Logger logger = LoggerFactory.getLogger( TokenFetcher.class );

	public TokenResponse fetchToken( ArcConfiguration arcConfiguration )
	{
		HttpClient httpClient = createPortalHttpClient( arcConfiguration );

		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, Endpoint.GENERATE_TOKEN )
				.urlFormParam( "username", arcConfiguration.getUsername() )
				.urlFormParam( "password", arcConfiguration.getPassword() )
				.urlFormParam( "referer", arcConfiguration.getReferer() )
				.build();

		TokenResponse tokenResponse = HttpExecutor.getResponse( httpClient, httpPost, TokenResponse.class );

		logger.info( "TokenResponse successful: {}", tokenResponse.isSuccess() );
		logger.info( "TokenResponse toString: {}", tokenResponse );

		return tokenResponse;
	}

	private static HttpClient createPortalHttpClient( ArcConfiguration arcConfiguration )
	{
		int portNumber = arcConfiguration.getPort();
		return HttpClientFactory.createHttpClient( portNumber );
	}
}
