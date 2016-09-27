package com.teslagov.joan.portal.community.group.update;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.community.group.Group;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * {}/sharing/rest/community/groups/{id}/update
 *
 * @author Kevin Chen
 */
public class GroupUpdater {
	public GroupUpdateResponse updateGroup(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		Group group
	) {
		String path = PortalEndpointFactory.SharingRest.Community.Groups.makeUpdateGroupPath(arcConfiguration, group.id);
		HttpPost httpPost =
			new HttpPostBuilder(path)
				.urlFormParam("token", tokenResponse.getToken())
				.urlFormParam("f", "json")
				.urlFormParam("clearEmptyFields", Boolean.TRUE.toString())
				.urlFormParam("title", group.title)
				.urlFormParam("description", group.description)
				.urlFormParam("snippet", group.snippet)
				.urlFormParam("tags", StringUtils.join(group.tags, ", "))
				.urlFormParam("phone", group.phone)
				.urlFormParam("access", group.access.getName())
				.urlFormParam("sortField", group.sortField.getName())
				.urlFormParam("sortOrder", group.sortOrder.getName())
				.urlFormParam("isViewOnly", group.isViewOnly)
				.urlFormParam("isInvitationOnly", group.isInvitationOnly)
				.urlFormParam("thumbnail", group.thumbnail)
				.build();

		return HttpExecutor.getResponse(httpClient, httpPost, GroupUpdateResponse.class);
	}
}
