package com.teslagov.joan.portal.group.delete;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.token.PortalTokenResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class GroupDeleter
{
	public GroupDeleteResponse deleteGroup(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		PortalTokenResponse portalTokenResponse,
		String groupID
	)
	{
		String path = PortalEndpointFactory.createDeleteGroupPath( arcConfiguration, groupID );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "token", portalTokenResponse.getToken() )
				.urlFormParam( "f", "json" )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, GroupDeleteResponse.class );
	}
}
