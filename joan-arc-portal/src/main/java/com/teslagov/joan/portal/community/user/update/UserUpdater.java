package com.teslagov.joan.portal.community.user.update;

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
public class UserUpdater {
	private static final Logger logger = LoggerFactory.getLogger(UserUpdater.class);

	public UserUpdateResponse updateUser(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String username,
	    String key,
	    String value
	) {
		String url = PortalEndpointFactory.SharingRest.Community.makeUpdateUserPath(arcConfiguration, username);
		logger.debug("Hitting url {} with token {}", url, tokenResponse.getToken());
		HttpPost httpPost =
			new HttpPostBuilder(url)
				.urlFormParam("f", "pjson")
				.urlFormParam(key, value)
				.build();

		httpPost.setHeader("cookie", "agwtoken=" + tokenResponse.getToken());

		return HttpExecutor.getResponse(httpClient, httpPost, UserUpdateResponse.class);
	}
}
