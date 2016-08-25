package com.teslagov.joan.portal.group.userremove;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.TokenResponse;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.group.Group;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import java.util.List;

/**
 * @author Kevin Chen
 */
public class GroupUserRemover
{
	public GroupUserRemoveResponse removeUsersFromGroup(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		Group group,
		List<String> usernames
	)
	{
		String path = PortalEndpointFactory.createRemoveUserToGroupPath( arcConfiguration, group.id );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "f", "json" )
				.urlFormParam( "users", StringUtils.join( usernames, "," ) )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, GroupUserRemoveResponse.class );
	}
}
