package com.teslagov.joan.portal.token;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class PortalTokenFetcher
{
	public PortalTokenResponse fetchToken( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		String path = PortalEndpointFactory.createGenerateTokenPath( arcConfiguration );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getPortalAdminUsername() )
				.urlFormParam( "password", arcConfiguration.getPortalAdminPassword() )
				.urlFormParam( "referer", arcConfiguration.getPortalAdminUsername() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, PortalTokenResponse.class );
	}
}
