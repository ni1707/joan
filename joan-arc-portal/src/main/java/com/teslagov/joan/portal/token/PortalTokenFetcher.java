package com.teslagov.joan.portal.token;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenFetcher;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * {}/sharing/rest/generateToken
 *
 * @author Kevin Chen
 */
public class PortalTokenFetcher implements TokenFetcher {
	private final HttpClient httpClient;

	private final ArcPortalConfiguration arcConfiguration;

	public PortalTokenFetcher(HttpClient httpClient, ArcPortalConfiguration arcConfiguration) {
		this.httpClient = httpClient;
		this.arcConfiguration = arcConfiguration;
	}

	@Override
	public TokenResponse fetchToken() {
		String path = PortalEndpointFactory.SharingRest.makeGenerateTokenPath(arcConfiguration);
		HttpPost httpPost =
			new HttpPostBuilder(path)
				.urlFormParam("f", "json")
				.urlFormParam("username", arcConfiguration.getPortalAdminUsername())
				.urlFormParam("password", arcConfiguration.getPortalAdminPassword())
				.urlFormParam("referer", arcConfiguration.getPortalAdminUsername())
				.build();

		return HttpExecutor.getResponse(httpClient, httpPost, PortalTokenResponse.class);
	}
}
