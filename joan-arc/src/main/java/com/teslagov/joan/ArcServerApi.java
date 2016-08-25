package com.teslagov.joan;

import com.teslagov.joan.server.token.ServerTokenFetcher;
import com.teslagov.joan.server.user.UserAddResponse;
import com.teslagov.joan.server.user.UserAdder;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public class ArcServerApi extends AbstractArcRestApi
{
	private final UserAdder userAdder = new UserAdder();

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

	public UserAddResponse addUser( User user )
	{
		refreshTokenIfNecessary();
		return userAdder.addUser( httpClient, arcConfiguration, tokenResponse, user );
	}
}
