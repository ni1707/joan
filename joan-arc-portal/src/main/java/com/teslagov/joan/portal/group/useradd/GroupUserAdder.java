package com.teslagov.joan.portal.group.useradd;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.token.PortalTokenResponse;
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
		PortalTokenResponse portalTokenResponse,
		Group group,
		List<String> usernames
	)
	{
		String path = PortalEndpointFactory.createAddUserToGroupPath( arcConfiguration, group.id );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "token", portalTokenResponse.getToken() )
				.urlFormParam( "f", "json" )
				.urlFormParam( "users", StringUtils.join( usernames, "," ) )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, GroupUserAddResponse.class );
	}
}
