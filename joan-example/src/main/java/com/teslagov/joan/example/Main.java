package com.teslagov.joan.example;

import com.teslagov.joan.api.ArcApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.Role;
import com.teslagov.joan.core.SortOrder;
import com.teslagov.joan.core.UserRequestModel;
import com.teslagov.joan.core.UserResponseModel;
import com.teslagov.joan.portal.models.ItemPublishModel;
import com.teslagov.joan.portal.models.ItemUploadModel;
import com.teslagov.joan.portal.community.group.Group;
import com.teslagov.joan.portal.community.group.GroupAccess;
import com.teslagov.joan.portal.community.group.GroupSortField;
import com.teslagov.joan.portal.community.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.community.user.fetch.UserListResponse;
import com.teslagov.props.Properties;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.teslagov.joan.api.ArcConfigurationBuilder.arcConfig;
import static com.teslagov.joan.portal.community.group.GroupBuilder.newGroup;
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

//		createGroupExample( arcApi )

		String username = UUID.randomUUID().toString();

		createNewUserExample( arcApi, username );

		String id = uploadItemExample( arcApi, username );

		//Publishing creates a new published item

		String publishedId = publishItemExample( arcApi, id, username );

		deleteItemExample( arcApi, id, username );

		deleteItemExample( arcApi, publishedId, username );

		removeUserExample( arcApi, username );
	}

	private static void createNewUserExample( ArcApi arcApi, String username )
	{
		// EMAIL must be supplied!
		UserRequestModel newUserRequestModel = newUser( username, "Password123!", username + "@example.com",
				Role.ORG_PUBLISHER, username, "Description", "Full Name" )
				.build();
//		arcApi.addUserViaServer( newUser );
		arcApi.addUserViaPortal( newUserRequestModel );
	}

	private static void removeUserExample( ArcApi arcApi, String username )
	{
		arcApi.removeUser( username );
	}

	private static String uploadItemExample( ArcApi arcApi, String username )
	{
		File file = new File(Main.class.getClassLoader().getResource("example.csv").getFile());

		ItemUploadModel itemUploadModel = new ItemUploadModel(file, "CSV")
				.text("This is an example file")
				.title("An example file")
				.typeKeywords("csv, map")
				.description("This example file is some cities")
				.tags("csv, cities, file")
				.snippet("A snippet about the file")
				.licenseInfo("Apache 2.0")
				.culture("US")
				.properties("some=properties")
				.extent("North America")
				.destinationItemId("Destination ID")
				.appCategories("mapping, points, interest")
				.industries("Tech")
				.languages("EN")
				.format("json");

		return arcApi.uploadItem(itemUploadModel, username).id;
	}

	private static String publishItemExample( ArcApi arcApi, String id, String username )
	{
		ItemPublishModel itemPublishModel = new ItemPublishModel(id, "CSV", "{\"name\":\"" + id + "\"}");
		return arcApi.publishItem(itemPublishModel, username ).services.get(0).serviceItemId;
	}

	private static void deleteItemExample( ArcApi arcApi, String id, String username )
	{
		arcApi.deleteItem( id, username );
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
