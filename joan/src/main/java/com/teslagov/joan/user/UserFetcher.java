package com.teslagov.joan.user;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalResponse;
import com.teslagov.joan.token.TokenResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class to retrieve a single page of users.
 *
 * @author Kevin Chen
 */
public class UserFetcher
{
	private static final Logger logger = LoggerFactory.getLogger( UserFetcher.class );

	public List<User> fetchUsers(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		PortalResponse portalResponse
	)
	{
		return fetchUsers( httpClient, arcConfiguration, tokenResponse, portalResponse, 0, 100 );
	}

	public List<User> fetchUsers(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		PortalResponse portalResponse,
		int start,
		int num
	)
	{
		String path = String.format( "%s/sharing/rest/portals/%s/users", arcConfiguration.getPortalUrl(), portalResponse.id );
		logger.debug( "Hitting path: {}", path );
		HttpPost httpPost =
			new HttpPostBuilder( arcConfiguration, path )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "start", start + "" )
				.urlFormParam( "num", num + "" )
				.build();

		UserListResponse userListResponse = HttpExecutor.getResponse( httpClient, httpPost, UserListResponse.class );
		return userListResponse.users;
	}
}
