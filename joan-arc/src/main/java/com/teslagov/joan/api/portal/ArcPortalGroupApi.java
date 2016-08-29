package com.teslagov.joan.api.portal;

import com.teslagov.joan.api.AbstractArcRestApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public class ArcPortalGroupApi extends AbstractArcRestApi
{
	public ArcPortalGroupApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager,
		String apiName
	)
	{
		super( httpClient, arcConfiguration, zoneOffset, tokenManager, apiName );
	}
}
