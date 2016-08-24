package com.teslagov.joan.portal.user;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.Provider;
import com.teslagov.joan.Response;
import com.teslagov.joan.Role;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.token.PortalTokenResponse;
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
		PortalTokenResponse portalTokenResponse,
		String username,
		String password,
		String firstName,
		String lastName,
		String fullName,
		String email,
		String description
	)
	{
		logger.debug( "Creating user with token: {}", portalTokenResponse.getToken() );
		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, "/portaladmin/security/users/createUser" )
				.urlFormParam( "f", "json" )
				.urlFormParam( "token", portalTokenResponse.getToken() )
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
