package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.TokenManager;
import com.teslagov.joan.portal.community.group.Group;
import com.teslagov.joan.portal.community.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.community.group.create.GroupCreator;
import com.teslagov.joan.portal.community.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.community.group.delete.GroupDeleter;
import com.teslagov.joan.portal.community.group.update.GroupUpdateResponse;
import com.teslagov.joan.portal.community.group.update.GroupUpdater;
import com.teslagov.joan.portal.community.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.community.group.useradd.GroupUserAdder;
import com.teslagov.joan.portal.community.group.userremove.GroupUserRemoveResponse;
import com.teslagov.joan.portal.community.group.userremove.GroupUserRemover;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.util.List;

/**
 * Api for dealing with Groups
 * Created by joncrain on 9/22/16.
 */
public class GroupApi extends AbstractArcRestApi {
	private static final Logger logger = LoggerFactory.getLogger(GroupApi.class);

	private final GroupCreator groupCreator = new GroupCreator();

	private final GroupDeleter groupDeleter = new GroupDeleter();

	private final GroupUpdater groupUpdater = new GroupUpdater();

	private final GroupUserAdder groupUserAdder = new GroupUserAdder();

	private final GroupUserRemover groupUserRemover = new GroupUserRemover();

	public GroupApi(
		HttpClient httpClient,
		ArcPortalConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager
	) {
		super(httpClient, arcConfiguration, zoneOffset, tokenManager, "Group Api");
	}

	public GroupCreateResponse createGroup(Group group) {
		refreshTokenIfNecessary();
		GroupCreateResponse groupCreateResponse = groupCreator.createGroup(httpClient, arcConfiguration, tokenManager.getTokenResponse(), group);
		logger.debug("GROUP ACCESS = {}", groupCreateResponse.group.access);

		return groupCreateResponse;
	}

	public GroupDeleteResponse deleteGroup(Group group) {
		return deleteGroup(group.id);
	}

	public GroupDeleteResponse deleteGroup(String groupID) {
		refreshTokenIfNecessary();
		return groupDeleter.deleteGroup(httpClient, arcConfiguration, tokenManager.getTokenResponse(), groupID);
	}

	public GroupUpdateResponse updateGroup(Group group) {
		refreshTokenIfNecessary();
		return groupUpdater.updateGroup(httpClient, arcConfiguration, tokenManager.getTokenResponse(), group);
	}

	public GroupUserAddResponse addUsersToGroup(Group group, List<String> usernames) {
		refreshTokenIfNecessary();
		return groupUserAdder.addUserToGroup(httpClient, arcConfiguration, tokenManager.getTokenResponse(), group, usernames);
	}

	public GroupUserRemoveResponse removeUsersFromGroup(Group group, List<String> usernames) {
		refreshTokenIfNecessary();
		return groupUserRemover.removeUsersFromGroup(httpClient, arcConfiguration, tokenManager.getTokenResponse(), group, usernames);
	}
}
