package com.teslagov.joan.portal.group.create;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.TokenResponse;
import com.teslagov.joan.http.HttpExecutor;
import com.teslagov.joan.http.HttpPostBuilder;
import com.teslagov.joan.portal.PortalEndpointFactory;
import com.teslagov.joan.portal.group.Group;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Kevin Chen
 */
public class GroupCreator
{
	public GroupCreateResponse createGroup(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		TokenResponse tokenResponse,
		Group group
	)
	{
		String path = PortalEndpointFactory.createCreateGroupPath( arcConfiguration );
		HttpPost httpPost =
			new HttpPostBuilder( path )
				.urlFormParam( "token", tokenResponse.getToken() )
				.urlFormParam( "f", "json" )
				.urlFormParam( "title", group.title )
				.urlFormParam( "description", group.description )
				.urlFormParam( "snippet", group.snippet )
				.urlFormParam( "tags", StringUtils.join( group.tags, ", " ) )
				.urlFormParam( "phone", group.phone )
				.urlFormParam( "access", group.access.getName() )
				.urlFormParam( "sortField", group.sortField.getName() )
				.urlFormParam( "sortOrder", group.sortOrder.getName() )
				.urlFormParam( "isViewOnly", group.isViewOnly )
				.urlFormParam( "isInvitationOnly", group.isInvitationOnly )
				.urlFormParam( "thumbnail", group.thumbnail )
				.build();

		return HttpExecutor.getResponse( httpClient, httpPost, GroupCreateResponse.class );
	}
}
