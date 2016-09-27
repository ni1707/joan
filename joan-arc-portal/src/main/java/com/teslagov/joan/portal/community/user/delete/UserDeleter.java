package com.teslagov.joan.portal.community.user.delete;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {}/sharing/rest/community/users/{id}/delete
 *
 * @author Jon Crain
 */
public class UserDeleter {
	private static final Logger logger = LoggerFactory.getLogger(UserDeleter.class);

	public UserDeleteResponse deleteUser(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String username
	) {
		String url = PortalEndpointFactory.SharingRest.Community.makeDeleteUserPath(arcConfiguration, username);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		HttpPost httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", "pjson")
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, UserDeleteResponse.class);
	}
}
