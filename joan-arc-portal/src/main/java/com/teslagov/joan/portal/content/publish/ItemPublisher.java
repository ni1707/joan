package com.teslagov.joan.portal.content.publish;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.models.ItemPublishModel;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/content/users/{id}/publish
 * Created by joncrain on 9/20/16.
 */
public class ItemPublisher {
	private static final Logger logger = LoggerFactory.getLogger(ItemPublisher.class);

	public ItemPublishResponse publishItem(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		ItemPublishModel itemPublishModel,
		String username
	) {
		String url = PortalEndpointFactory.SharingRest.Content.makePublishItemPath(arcConfiguration, username);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		logger.debug("Publishing: {}", itemPublishModel);

		HttpPost httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", "json")
				.urlFormParam("itemid", itemPublishModel.getId())
				.urlFormParam("filetype", itemPublishModel.getType())
				.urlFormParam("publishParameters", itemPublishModel.getPublishParameters())
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, ItemPublishResponse.class);
	}
}
