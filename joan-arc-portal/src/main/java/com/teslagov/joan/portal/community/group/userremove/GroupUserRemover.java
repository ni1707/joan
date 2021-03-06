package com.teslagov.joan.portal.community.group.userremove;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.community.group.Group;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import java.util.List;

/**
 * {}/sharing/rest/community/groups/{id}/removeUsers
 * This class doesn't have a response because Portal doesn't provide one
 *
 * @author Kevin Chen
 * @author Jon Crain
 */
public class GroupUserRemover {
	public void removeUsersFromGroup(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		Group group,
		List<String> usernames
	) {
		String path = PortalEndpointFactory.SharingRest.Community.Groups.makeRemoveUserToGroupPath(arcConfiguration, group.id);
		HttpPost httpPost =
			new HttpPostBuilder(path)
				.urlFormParam("token", tokenResponse.getToken())
				.urlFormParam("f", "json")
				.urlFormParam("users", StringUtils.join(usernames, ","))
				.build();

		try {
			HttpExecutor.getStringResponse(httpClient, httpPost);
		} catch( Exception e ) {
			//We're going to eat this exception because there will never be a response
		}
	}
}
