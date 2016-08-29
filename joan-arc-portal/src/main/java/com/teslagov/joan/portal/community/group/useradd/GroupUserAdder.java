package com.teslagov.joan.portal.community.group.useradd;

import com.teslagov.joan.core.ArcConfiguration;
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
 * @author Kevin Chen
 */
public class GroupUserAdder
{
	public GroupUserAddResponse addUserToGroup(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		Group group,
		List<String> usernames
	)
	{
		String path = PortalEndpointFactory.SharingRest.Community.Groups.makeAddUserToGroupPath( arcConfiguration, group.id );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "f", "json" )
				.urlFormParam( "users", StringUtils.join( usernames, "," ) )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, GroupUserAddResponse.class );
	}
}
