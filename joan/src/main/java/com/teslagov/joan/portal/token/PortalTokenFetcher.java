package com.teslagov.joan.portal.token;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpoint;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class PortalTokenFetcher
{
	public PortalTokenResponse fetchToken( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		String path = arcConfiguration.getPortalUrl() + PortalEndpoint.GENERATE_TOKEN.getEndpointPath();
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getPortalUserName() )
				.urlFormParam( "password", arcConfiguration.getPortalUserPassword() )
				.urlFormParam( "referer", arcConfiguration.getPortalUserName() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, PortalTokenResponse.class );
	}
}
