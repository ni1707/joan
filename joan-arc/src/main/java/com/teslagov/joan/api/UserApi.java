package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.portal.community.user.create.UserCreateResponse;
import com.teslagov.joan.portal.community.user.create.UserCreator;
import com.teslagov.joan.portal.community.user.delete.UserDeleteResponse;
import com.teslagov.joan.portal.community.user.delete.UserDeleter;
import com.teslagov.joan.portal.community.user.fetch.UserFetcher;
import com.teslagov.joan.portal.community.user.fetch.UserListResponse;
import com.teslagov.joan.portal.portal.self.PortalFetcher;
import com.teslagov.joan.portal.portal.self.PortalResponse;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;

/**
 * Api for dealing with Users
 * Created by joncrain on 9/22/16.
 */
public class UserApi extends AbstractArcRestApi {
	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

	private final UserFetcher userFetcher = new UserFetcher();

	private final UserCreator userCreator = new UserCreator();

	private final UserDeleter userDeleter = new UserDeleter();

	private final PortalFetcher portalFetcher = new PortalFetcher();

	private PortalResponse portalResponse;

	public UserApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager
	) {
		super(httpClient, arcConfiguration, zoneOffset, tokenManager, "User Api");
	}

	public UserCreateResponse addUser(UserRequestModel userRequestModel) {
		refreshTokenIfNecessary();
		return userCreator.createUser(httpClient, arcConfiguration, tokenManager.getTokenResponse(), userRequestModel);
	}

	public UserDeleteResponse deleteUser(String username) {
		refreshTokenIfNecessary();
		return userDeleter.deleteUser(httpClient, arcConfiguration, tokenManager.getTokenResponse(), username);
	}


	public UserListResponse fetchUsers() {
		return fetchUsers(0, 100);
	}

	public UserListResponse fetchUsers(int start, int num) {
		refreshTokenIfNecessary();

		if (portalResponse == null) {
			getPortal();
		}

		return userFetcher.fetchUsers(httpClient, arcConfiguration, tokenManager.getTokenResponse(), portalResponse, start, num);
	}

	public void getPortal() {
		refreshTokenIfNecessary();
		portalResponse = portalFetcher.fetchPortal(httpClient, arcConfiguration, tokenManager.getTokenResponse());
		logger.debug("Portal ID = {}", portalResponse.id);
	}
}
