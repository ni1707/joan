package com.teslagov.joan.user;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Provider;
import com.teslagov.joan.Response;
import com.teslagov.joan.Role;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.token.TokenResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin Chen
 */
public class UserCreator
{
	private static final Logger logger = LoggerFactory.getLogger( UserCreator.class );

	public Response createArcGisUser(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String username,
		String password,
		String firstName,
		String lastName,
		String fullName,
		String email,
		String description
	)
	{
		logger.debug( "Creating user with token: {}", tokenResponse.getToken() );
		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, "/portaladmin/security/users/createUser" )
				.urlFormParam( "f", "json" )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "username", username )
				.urlFormParam( "password", password )
				.urlFormParam( "firstname", firstName )
				.urlFormParam( "lastname", lastName )
				.urlFormParam( "referer", arcConfiguration.getPortalUserName() )
				.urlFormParam( "fullname", fullName )
				.urlFormParam( "email", email )
				.urlFormParam( "description", description )
				.urlFormParam( "role", Role.ORG_USER.getName() )
				.urlFormParam( "provider", Provider.ARC_GIS.getName() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, Response.class );
	}
}
