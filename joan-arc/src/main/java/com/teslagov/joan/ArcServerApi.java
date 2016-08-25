package com.teslagov.joan;

import com.teslagov.joan.server.token.ServerTokenFetcher;
import com.teslagov.joan.server.user.UserAddResponse;
import com.teslagov.joan.server.user.UserAdder;
import org.apache.http.client.HttpClient;

/**
 * @author Kevin Chen
 */
public class ArcServerApi
{
	private final HttpClient httpClient;

	private final ArcConfiguration arcConfiguration;

	private final ServerTokenFetcher serverTokenFetcher = new ServerTokenFetcher();

	private final UserAdder userAdder = new UserAdder();

	public ArcServerApi( HttpClient httpClient, ArcConfiguration arcConfiguration )
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
	}

	public UserAddResponse addUser( User user )
	{
		return userAdder.addUser( httpClient, arcConfiguration, user );
	}
}
