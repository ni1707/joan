package com.teslagov.joan;

import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.GroupAccess;
import com.teslagov.joan.portal.group.GroupSortField;
import org.apache.http.client.HttpClient;

import static com.teslagov.joan.portal.group.GroupBuilder.newGroup;

/**
 * @author Kevin Chen
 */
public class Main
{
	public static void main( String[] args )
	{
		ArcConfiguration arcConfiguration = ArcConfigurationFactory.createArcConfiguration();

		HttpClient httpClient = TrustingHttpClientFactory.createVeryUnsafePortalHttpClient( arcConfiguration );

		ArcApi arcApi = new ArcApi( httpClient, arcConfiguration );
		arcApi.fetchToken();
		arcApi.fetchUsers();

		Group group = newGroup()
			.title( "Kevin's Test Group 5" )
			.description( "A test group owned by Kevin" )
			.snippet( "snippet..." )
			.tag( "tag1" ).tag( "tag2" ).tag( "tag3" )
			.phone( "1600 Pennsylvania Ave" )
			.access( GroupAccess.PUBLIC )
			.sortField( GroupSortField.TITLE )
			.sortOrder( SortOrder.ASCENDING )
			.isViewOnly( true )
			.isInvitationOnly( false )
			.thumbnail( "" )
			.build();
		arcApi.createGroup( group );
	}
}
