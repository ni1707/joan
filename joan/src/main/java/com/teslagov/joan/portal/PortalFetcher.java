package com.teslagov.joan.portal;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Endpoint;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.token.TokenResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class PortalFetcher
{
	public PortalResponse fetchPortal( HttpClient httpClient, ArcConfiguration arcConfiguration, TokenResponse tokenResponse )
	{
		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, Endpoint.PORTAL_ID.getEndpointPath() )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getUsername() )
				.urlFormParam( "password", arcConfiguration.getPassword() )
				.urlFormParam( "referer", arcConfiguration.getReferer() )
				.urlFormParam( "token", tokenResponse.getToken() )
		.build();

		return HttpExecutor.getResponse( httpClient, httpPost, PortalResponse.class );
	}
}
