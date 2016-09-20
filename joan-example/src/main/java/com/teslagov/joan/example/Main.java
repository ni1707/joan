package com.teslagov.joan.example;

import com.teslagov.joan.api.ArcApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.Role;
import com.teslagov.joan.core.SortOrder;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.core.UserResponseModel;
import com.teslagov.joan.portal.models.ItemUploadModel;
import com.teslagov.joan.portal.sharing.community.group.Group;
import com.teslagov.joan.portal.sharing.community.group.GroupAccess;
import com.teslagov.joan.portal.sharing.community.group.GroupSortField;
import com.teslagov.joan.portal.sharing.community.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.sharing.user.fetch.UserListResponse;
import com.teslagov.props.Properties;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.teslagov.joan.api.ArcConfigurationBuilder.arcConfig;
import static com.teslagov.joan.portal.sharing.community.group.GroupBuilder.newGroup;
import static com.teslagov.joan.core.UserRequestModel.newUser;

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

		UserListResponse userListResponse = arcApi.fetchUsers();
		if ( userListResponse.isSuccess() )
		{
			List<UserResponseModel> users = userListResponse.users;
			users.forEach( u -> logger.debug( "User {}", u ) );
		}

//		createGroupExample( arcApi );

		createNewUserExample( arcApi );

		uploadItemExample( arcApi );

		removeUserExample( arcApi );
	}

	private static void createNewUserExample( ArcApi arcApi )
	{
		// EMAIL must be supplied!
		String username = "jon.snow3";
		UserRequestModel newUserRequestModel = newUser( username, "Password123!", username + "@gmail.com",
				Role.ORG_USER, "Account ID", "Description", "Full Name" )
				.build();
//		arcApi.addUserViaServer( newUser );
		arcApi.addUserViaPortal( newUserRequestModel );
	}

	private static void removeUserExample( ArcApi arcApi )
	{
		arcApi.removeUser( "jon.snow3" );
	}

	private static void uploadItemExample( ArcApi arcApi )
	{
		File file = new File("example.csv");
		ItemUploadModel itemUploadModel = new ItemUploadModel(file, null, "Text", null, "Title", null, null, null, null,
				null, "Description", "Tags", "Snippet", "License", "Culture", null, "Extent", "Callback", "Id", null,
				null, null, null, null, "Categories", "Industries", "Langs", null, null, null, null, null, null,
				null, "pjson");
		arcApi.uploadItem(itemUploadModel, "jon.snow3");
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
