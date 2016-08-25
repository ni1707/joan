package com.teslagov.joan.portal.group.delete;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenResponse;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
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
		TokenResponse tokenResponse,
		String groupID
	)
	{
		String path = PortalEndpointFactory.createDeleteGroupPath( arcConfiguration, groupID );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "f", "json" )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, GroupDeleteResponse.class );
	}
}
