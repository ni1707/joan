package com.teslagov.joan;

import com.teslagov.joan.portal.group.Group;
import com.teslagov.joan.portal.group.GroupAccess;
import com.teslagov.joan.portal.group.GroupSortField;
import com.teslagov.joan.portal.group.useradd.GroupUserAddResponse;
import com.teslagov.properties.Properties;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.teslagov.joan.ArcConfigurationBuilder.arcConfig;
import static com.teslagov.joan.portal.group.GroupBuilder.newGroup;
import static com.teslagov.joan.User.newUser;

/**
 * @author Kevin Chen
 */
public class Main
{
	private static final Logger logger = LoggerFactory.getLogger( Main.class );

	public static void main( String[] args ) throws InterruptedException
	{
		Properties properties = ArcPropertiesFactory.createArcProperties();

		ArcConfiguration arcConfiguration =
			arcConfig()
				.portalAdminUsername( properties.getString( ArcProperties.PORTAL_ADMIN_USERNAME ) )
				.portalAdminPassword( properties.getString( ArcProperties.PORTAL_ADMIN_PASSWORD ) )
				.portalUrl( properties.getString( ArcProperties.PORTAL_URL ) )
				.portalPort( properties.getInteger( ArcProperties.PORTAL_PORT ) )
				.arcServerAdminUsername( properties.getString( ArcProperties.ARC_GIS_SERVER_ADMIN_USERNAME ) )
				.arcServerAdminPassword( properties.getString( ArcProperties.ARC_GIS_SERVER_ADMIN_PASSWORD ) )
				.arcServerUrl( properties.getString( ArcProperties.ARC_GIS_SERVER_URL ) )
				.arcServerPort( properties.getInteger( ArcProperties.ARC_GIS_SERVER_PORT ) )
				.build();

		HttpClient httpClient = TrustingHttpClientFactory.createVeryUnsafePortalHttpClient( arcConfiguration );

		ArcApi arcApi = new ArcApi( httpClient, arcConfiguration );

//		arcApi.fetchUsers();

//		createGroupExample( arcApi );

		createNewUserExample( arcApi );
	}

	private static void createNewUserExample( ArcApi arcApi )
	{
		User newUser = newUser( "jon.snow", "Password123!" ).build();
		arcApi.addUser( newUser );
	}

	private static void createGroupExample( ArcApi arcApi )
	{
		Group group = newGroup()
			.title( "GOT 2" )
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

		group = arcApi.createGroup( group ).group;

		logger.info( "Created Group {}", group.id );

		GroupUserAddResponse groupUserAddResponse = arcApi.addUsersToGroup( group, Arrays.asList( "david.grosso", "modibo" ) );

		arcApi.removeUsersFromGroup( group, Arrays.asList( "david.grosso" ) );

//		GroupDeleteResponse groupDeleteResponse = arcApi.deleteGroup( group );
//		logger.info( "Deleted Group {}", groupDeleteResponse.groupId );
	}
}
