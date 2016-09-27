package com.teslagov.joan.portal.content.fetch;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/content/users/{id}?f=pjson
 * Created by joncrain on 9/26/16.
 */
public class ItemFetcher {
	private static final Logger logger = LoggerFactory.getLogger(com.teslagov.joan.portal.content.delete.ItemDeleter.class);

	public ItemFetchResponse fetchItems(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String username
	) {
		String url = PortalEndpointFactory.SharingRest.Content.makeFetchItemPath(arcConfiguration, username);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		logger.debug("Fetching items for user: {}", username);

		HttpGet httpGet = new HttpGet(url);

		httpGet.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpGet, ItemFetchResponse.class);
	}
}
