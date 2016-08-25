package com.teslagov.joan.server.user;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.User;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.server.ServerEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class UserAdder
{
	public UserAddResponse addUser(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		User user
	)
	{
		String url = ServerEndpointFactory.SecurityEndpointFactory.UserEndpointFactory.createAddUserPath( arcConfiguration );
		HttpPost httpPost =
			new HttpPostBuilder( url )
				.urlFormParam( "f", "json" )
				.urlFormParam( "client", "referer" )
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
