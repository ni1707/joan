package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenRefresher;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.server.token.ServerTokenFetcher;
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

	public ArcServerApi( HttpClient httpClient, ArcConfiguration arcConfiguration, ZoneOffset zoneOffset )
	{
		super(
			httpClient,
			arcConfiguration,
			zoneOffset,
			new TokenRefresher(
				new ServerTokenFetcher(
					httpClient,
					arcConfiguration
				),
				zoneOffset
			),
			"ArcGIS Server"
		);
	}

	public UserAddResponse addUser( UserRequestModel userRequestModel )
	{
		refreshTokenIfNecessary();
		return userAdder.addUser( httpClient, arcConfiguration, tokenResponse, userRequestModel );
	}

	public UserRemoveResponse removeUser( String username )
	{
		refreshTokenIfNecessary();
		return userRemover.removeUser( httpClient, arcConfiguration, tokenResponse, username );
	}
}