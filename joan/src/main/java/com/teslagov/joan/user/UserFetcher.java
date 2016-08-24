package com.teslagov.joan.user;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalResponse;
import com.teslagov.joan.token.TokenResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
		String path = String.format(
			"%s/sharing/rest/portals/%s/users?token=%s&f=json&start=%s&num=%s",
			arcConfiguration.getPortalUrl(),
			portalResponse.id,
			tokenResponse.getToken(),
			start + "",
			num + ""
		);

		logger.debug( "Hitting path: {}", path );

		HttpGet httpGet = new HttpGet( path );

		UserListResponse userListResponse = HttpExecutor.getResponse( httpClient, httpGet, UserListResponse.class );
		return userListResponse.users;
	}
}
