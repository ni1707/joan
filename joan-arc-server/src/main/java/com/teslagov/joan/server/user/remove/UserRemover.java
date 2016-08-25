package com.teslagov.joan.server.user.remove;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.User;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.server.ServerEndpointFactory;
import com.teslagov.joan.server.user.add.UserAddResponse;
import com.teslagov.joan.server.user.add.UserAdder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Chen
 */
public class UserRemover
{
	private static final Logger logger = LoggerFactory.getLogger( UserAdder.class );

	public UserRemoveResponse removeUser(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String username
	)
	{
		String url = ServerEndpointFactory.SecurityEndpointFactory.UserEndpointFactory.createRemoveUserPath( arcConfiguration );
		logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
		HttpPost httpPost =
			new HttpPostBuilder( url )
				.urlFormParam( "f", "json" )
				.urlFormParam( "client", "referer" )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "referer", arcConfiguration.getArcServerAdminUsername() )
				.urlFormParam( "username", username )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, UserRemoveResponse.class );
	}
}
