package com.teslagov.joan.portal.user.fetch;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.UserResponseModel;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.portal.PortalResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

	public List<UserResponseModel> fetchUsers(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		PortalResponse portalResponse
	)
	{
		return fetchUsers( httpClient, arcConfiguration, tokenResponse, portalResponse, 0, 100 );
	}

	public List<UserResponseModel> fetchUsers(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		PortalResponse portalResponse,
		int start,
		int num
	)
	{
		String path = String.format(
			"%s?token=%s&f=json&start=%s&num=%s",
			PortalEndpointFactory.createFetchUsersPath( arcConfiguration, portalResponse.id ),
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