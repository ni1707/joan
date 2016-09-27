package com.teslagov.joan.portal.content.share;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/content/users/{id}/items/{id}/delete
 * Created by joncrain on 9/21/16.
 */
public class ItemSharer {
	private static final Logger logger = LoggerFactory.getLogger(ItemSharer.class);

	public ItemShareResponse shareItem(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String id,
		String username,
		String groups
	) {
		String url = PortalEndpointFactory.SharingRest.Content.makeShareItemPath(arcConfiguration, id, username);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		logger.debug("Sharing Item: {}", id);

		HttpPost httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", "json")
				.urlFormParam("everyone", false)
				.urlFormParam("org", false)
				.urlFormParam("groups", groups)
				.urlFormParam("confirmItemControl", false)
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, ItemShareResponse.class);
	}
}
