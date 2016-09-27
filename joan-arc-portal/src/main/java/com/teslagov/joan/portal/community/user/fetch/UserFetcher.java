package com.teslagov.joan.portal.community.user.fetch;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.portal.self.PortalResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to retrieve a single page of users.
 * {}/sharing/rest/community/users
 *
 * @author Kevin Chen
 */
public class UserFetcher {
	private static final Logger logger = LoggerFactory.getLogger(UserFetcher.class);

	public UserListResponse fetchUsers(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		PortalResponse portalResponse
	) {
		return fetchUsers(httpClient, arcConfiguration, tokenResponse, portalResponse, 0, 100);
	}

	public UserListResponse fetchUsers(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		PortalResponse portalResponse,
		int start,
		int num
	) {
		String path = String.format(
			"%s?token=%s&f=json&start=%s&num=%s",
			PortalEndpointFactory.SharingRest.Portals.makeFetchUsersPath(arcConfiguration, portalResponse.id),
			tokenResponse.getToken(),
			start + "",
			num + ""
		);

		logger.debug("Hitting path: {}", path);

		HttpGet httpGet = new HttpGet(path);

		UserListResponse userListResponse = HttpExecutor.getResponse(httpClient, httpGet, UserListResponse.class);
		return userListResponse;
	}
}
