package com.teslagov.joan.token;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Endpoint;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class TokenFetcher
{
	public TokenResponse fetchToken( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, Endpoint.GENERATE_TOKEN.getEndpointPath() )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getUsername() )
				.urlFormParam( "password", arcConfiguration.getPassword() )
				.urlFormParam( "referer", arcConfiguration.getReferer() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, TokenResponse.class );
	}
}
