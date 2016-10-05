package com.teslagov.joan.portal.community.group.fetchUsers;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.portal.PortalEndpointFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by joncrain on 9/29/16.
 */
public class GroupUserFetcher {
	public GroupUserFetchResponse fetchGroupUsers(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		String id
	) {
		String path = PortalEndpointFactory.SharingRest.Community.Groups.makeFetchUsersPath(arcConfiguration, id);
		HttpGet httpGet = new HttpGet(path);

		return HttpExecutor.getResponse(httpClient, httpGet, GroupUserFetchResponse.class);
	}
}
