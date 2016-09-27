package com.teslagov.joan.portal.content.upload;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.models.ItemUploadModel;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/content/users/{id}/addItem
 * Created by joncrain on 9/20/16.
 */
public class ItemUploader {
	private static final Logger logger = LoggerFactory.getLogger(ItemUploader.class);

	public ItemUploadResponse uploadItem(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		ItemUploadModel itemUploadModel,
		String username
	) {
		String url = PortalEndpointFactory.SharingRest.Content.makeUploadItemPath(arcConfiguration, username);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		logger.debug("Uploading: {}", itemUploadModel);

		HttpPost httpPost = new HttpPostBuilder(url).build();

		httpPost.setEntity(itemUploadModel.getHttpEntity());

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, ItemUploadResponse.class);
	}

	private String nullToEmpty(String s) {
		if (s == null) return "";
		return s;
	}
}
