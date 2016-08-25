package com.teslagov.joan.portal.portal;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.token.PortalTokenResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class PortalFetcher
{
	public PortalResponse fetchPortal( HttpClient httpClient, ArcConfiguration arcConfiguration, PortalTokenResponse portalTokenResponse )
	{
		String path = PortalEndpointFactory.createGetPortalIDPath( arcConfiguration );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getPortalAdminUsername() )
				.urlFormParam( "password", arcConfiguration.getPortalAdminPassword() )
				.urlFormParam( "referer", arcConfiguration.getPortalAdminUsername() )
				.urlFormParam( "token", portalTokenResponse.getToken() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, PortalResponse.class );
	}
}
