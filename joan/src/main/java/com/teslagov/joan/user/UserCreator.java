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

/**
 * @author Kevin Chen
 */
public class UserCreator
{
	public Response createArcGisUser(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String username,
		String password,
		String fullName,
		String email,
		String description
	)
	{
		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, "/arcgis/portaladmin/security/users/createUser" )
				.urlFormParam( "f", "json" )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "username", username )
				.urlFormParam( "password", password )
				.urlFormParam( "referer", arcConfiguration.getReferer() )
				.urlFormParam( "fullname", fullName )
				.urlFormParam( "email", email )
				.urlFormParam( "description", description )
				.urlFormParam( "role", Role.ORG_USER.getName() )
				.urlFormParam( "provider", Provider.ARC_GIS.getName() )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, Response.class );
	}
}
