package com.teslagov.joan;

import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public abstract class AbstractArcRestApi
{
	protected TokenResponse tokenResponse;

	protected final HttpClient httpClient;

	protected final ArcConfiguration arcConfiguration;

	protected final ZoneOffset zoneOffset;

	protected final TokenRefresher tokenRefresher;

	protected final String apiName;

	public AbstractArcRestApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenRefresher tokenRefresher,
		String apiName
	)
	{
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
		this.zoneOffset = zoneOffset;
		this.tokenRefresher = tokenRefresher;
		this.apiName = apiName;
	}

	protected void refreshTokenIfNecessary()
	{
		if ( tokenRefresher.isTokenExpired( tokenResponse ) )
		{
			tokenResponse = tokenRefresher.fetchToken();
		}
	}
}
