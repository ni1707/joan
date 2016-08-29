package com.teslagov.joan.api.server;

import com.teslagov.joan.api.AbstractArcRestApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.server.user.add.UserAddResponse;
import com.teslagov.joan.server.user.add.UserAdder;
import com.teslagov.joan.server.user.remove.UserRemoveResponse;
import com.teslagov.joan.server.user.remove.UserRemover;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public class ArcServerApi extends AbstractArcRestApi
{
	private final UserAdder userAdder = new UserAdder();

	private final UserRemover userRemover = new UserRemover();

	public ArcServerApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager
	)
	{
		super(
			httpClient,
			arcConfiguration,
			zoneOffset,
			tokenManager,
			"ArcGIS Server"
		);
	}

	public UserAddResponse addUser( UserRequestModel userRequestModel )
	{
		refreshTokenIfNecessary();
		return userAdder.addUser( httpClient, arcConfiguration, tokenManager.getTokenResponse(), userRequestModel );
	}

	public UserRemoveResponse removeUser( String username )
	{
		refreshTokenIfNecessary();
		return userRemover.removeUser( httpClient, arcConfiguration, tokenManager.getTokenResponse(), username );
	}
}
