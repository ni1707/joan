package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenManager;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public abstract class AbstractArcRestApi {
	protected final HttpClient httpClient;

	protected final ArcPortalConfiguration arcConfiguration;

	protected final ZoneOffset zoneOffset;

	protected final TokenManager tokenManager;

	protected final String apiName;

	public AbstractArcRestApi(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager,
		String apiName
	) {
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
		this.zoneOffset = zoneOffset;
		this.tokenManager = tokenManager;
		this.apiName = apiName;
	}

	protected void refreshTokenIfNecessary() {
		tokenManager.refreshTokenIfNecessary();
	}
}
