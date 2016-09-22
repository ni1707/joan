package com.teslagov.joan.portal.portal.self;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * {}/sharing/rest/portals/self
 * @author Kevin Chen
 */
public class PortalFetcher
{
	public PortalResponse fetchPortal( HttpClient httpClient, ArcConfiguration arcConfiguration, TokenResponse tokenResponse )
	{
		String path = PortalEndpointFactory.SharingRest.Portals.makeGetPortalIDPath( arcConfiguration );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getPortalAdminUsername() )
				.urlFormParam( "password", arcConfiguration.getPortalAdminPassword() )
				.urlFormParam( "referer", arcConfiguration.getPortalAdminUsername() )
				.urlFormParam( "token", tokenResponse.getToken() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, PortalResponse.class );
	}
}
