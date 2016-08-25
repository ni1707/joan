package com.teslagov.joan.portal.user.add;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.User;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Chen
 */
public class UserAdder
{
	private static final Logger logger = LoggerFactory.getLogger( UserAdder.class );

	public UserAddResponse addUser(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		User user
	)
	{
		String url = PortalEndpointFactory.PortalAdmin.Security.createCreateUserPath( arcConfiguration );
		logger.debug( "Hitting url {} with token {}", url, tokenResponse.getToken() );
		HttpPost httpPost =
			new HttpPostBuilder( url )
				.urlFormParam( "f", "json" )
				.urlFormParam( "client", "referer" )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "referer", arcConfiguration.getArcServerAdminUsername() )
				.urlFormParam( "username", user.getUsername() )
				.urlFormParam( "password", user.getPassword() )
				.urlFormParam( "fullname", user.getFullname() )
				.urlFormParam( "description", user.getDescription() )
				.urlFormParam( "email", user.getEmail() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, UserAddResponse.class );
	}
}
