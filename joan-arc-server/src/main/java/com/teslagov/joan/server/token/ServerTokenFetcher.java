package com.teslagov.joan.server.token;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.server.ServerEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * Generates a token with a POST to http://server:port/arcgis/admin/generateToken.
 * The generated security token can be used by clients when working with the Administrator API.
 *
 * @author Kevin Chen
 */
public class ServerTokenFetcher
{
	public ServerTokenResponse fetchServer(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		int tokenLifeMinutes
	)
	{
		String url = ServerEndpointFactory.createGenerateTokenEndpoint( arcConfiguration );
		HttpPost httpPost =
			new HttpPostBuilder( url )
				.urlFormParam( "f", "json" )
				.urlFormParam( "username", arcConfiguration.getArcServerAdminUsername() )
				.urlFormParam( "password", arcConfiguration.getArcServerAdminPassword() )
				.urlFormParam( "client", "referer" )
				.urlFormParam( "referer", arcConfiguration.getArcServerAdminUsername() )
				.urlFormParam( "expiration", tokenLifeMinutes )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, ServerTokenResponse.class );
	}
}
