package com.teslagov.joan.portal.sharing.token;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenFetcher;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class PortalTokenFetcher implements TokenFetcher
{
	private final HttpClient httpClient;

	private final ArcConfiguration arcConfiguration;

	public PortalTokenFetcher( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
	}

	@Override
	public TokenResponse fetchToken()
	{
		String path = PortalEndpointFactory.SharingRest.makeGenerateTokenPath( arcConfiguration );
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
