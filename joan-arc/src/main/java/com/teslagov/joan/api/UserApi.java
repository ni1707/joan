package com.teslagov.joan.api;

import com.teslagov.joan.core.*;
import com.teslagov.joan.portal.community.user.create.UserCreateResponse;
import com.teslagov.joan.portal.community.user.create.UserCreator;
import com.teslagov.joan.portal.community.user.delete.UserDeleteResponse;
import com.teslagov.joan.portal.community.user.delete.UserDeleter;
import com.teslagov.joan.portal.community.user.fetch.UserFetcher;
import com.teslagov.joan.portal.community.user.fetch.UserListResponse;
import com.teslagov.joan.portal.community.user.update.UserUpdateResponse;
import com.teslagov.joan.portal.community.user.update.UserUpdater;
import com.teslagov.joan.portal.portal.self.PortalFetcher;
import com.teslagov.joan.portal.portal.self.PortalResponse;
import org.apache.http.client.CookieStore;
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

	private final UserUpdater userUpdater = new UserUpdater();

	private PortalResponse portalResponse;

	public UserApi(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager
	) {
		super(httpClient, arcConfiguration, zoneOffset, tokenManager, "User Api");
	}

	public UserCreateResponse addUser(UserRequestModel userRequestModel) {
		refreshTokenIfNecessary();
		return userCreator.createUser(httpClient, arcConfiguration, tokenManager.getTokenResponse(), userRequestModel);
	}

	public UserCreateResponse adminAddUser(UserAdminRequestModel userAdminRequestModel, CookieStore cookieStore) {
		return userCreator.adminCreateUser(httpClient, arcConfiguration, userAdminRequestModel, cookieStore);
	}

	public UserDeleteResponse deleteUser(String username) {
		refreshTokenIfNecessary();
		return userDeleter.deleteUser(httpClient, arcConfiguration, tokenManager.getTokenResponse(), username);
	}

	public UserUpdateResponse updateUser(String username, String key, String value) {
		refreshTokenIfNecessary();
		return userUpdater.updateUser(httpClient, arcConfiguration, tokenManager.getTokenResponse(), username, key, value);
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
