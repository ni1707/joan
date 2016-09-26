package com.teslagov.joan.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.teslagov.joan.api.ArcPortalApi;
import com.teslagov.joan.api.ArcPortalApi;
import com.teslagov.joan.core.*;
import com.teslagov.joan.portal.community.group.delete.GroupDeleteResponse;
import com.teslagov.joan.portal.content.analyze.ItemAnalyzeResponse;
import com.teslagov.joan.portal.content.fetch.ItemFetchResponse;
import com.teslagov.joan.portal.models.ItemPublishModel;
import com.teslagov.joan.portal.models.ItemUploadModel;
import com.teslagov.joan.portal.community.group.Group;
import com.teslagov.joan.portal.community.group.GroupAccess;
import com.teslagov.joan.portal.community.group.GroupSortField;
import com.teslagov.joan.portal.community.group.useradd.GroupUserAddResponse;
import com.teslagov.joan.portal.community.user.fetch.UserListResponse;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.props.Properties;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.teslagov.joan.api.ArcConfigurationBuilder.arcConfig;
import static com.teslagov.joan.portal.community.group.GroupBuilder.newGroup;
import static com.teslagov.joan.core.UserRequestModel.newUser;
import static java.lang.Thread.sleep;

/**
 * @author Kevin Chen
 */
public class Main
{
	private static final Logger logger = LoggerFactory.getLogger( Main.class );

	public static void main( String[] args ) throws Exception
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

		ArcPortalApi arcPortalApi = new ArcPortalApi( httpClient, arcConfiguration, ZoneOffset.UTC,
                new TokenManager(
                        new TokenRefresher(
                                new PortalTokenFetcher(httpClient, arcConfiguration), ZoneOffset.UTC
                        )
                )
        );

		UserListResponse userListResponse = arcPortalApi.userApi.fetchUsers();
		if ( userListResponse.isSuccess() )
		{
			List<UserResponseModel> users = userListResponse.users;
			users.forEach( u -> logger.debug( "User {}", u ) );
		}

		String username = UUID.randomUUID().toString();
		String id = null;
		String publishedId = null;
		String groupId = null;

		try
		{
			groupId = createGroupExample(arcPortalApi);

			createNewUserExample(arcPortalApi, username);

			id = uploadItemExample(arcPortalApi, username);

			String analyzeResponse = arcPortalApi.itemApi.analyzeItem(id);

			publishedId = publishItemExample(arcPortalApi, id, username, analyzeResponse);

			shareItemExample(arcPortalApi, publishedId, username, groupId);

			deleteItemExample(arcPortalApi, id, username);

			deleteItemExample(arcPortalApi, publishedId, username);

			removeUserExample(arcPortalApi, username);

			deleteGroupExample(arcPortalApi, groupId);
		}
		catch (Exception e)
		{
			if (id != null)
			{
				arcPortalApi.itemApi.deleteItem(id, username);
			}

			if (publishedId != null)
			{
				arcPortalApi.itemApi.deleteItem(publishedId, username);
			}

			if (groupId != null)
			{
				arcPortalApi.groupApi.deleteGroup(groupId);
			}

			arcPortalApi.userApi.deleteUser(username);

			logger.debug("Exception occured {}", e.getMessage());
		}
	}

	private static void createNewUserExample( ArcPortalApi arcPortalApi, String username )
	{
		// EMAIL must be supplied!
		UserRequestModel newUserRequestModel = newUser( username, "Password123!", username + "@example.com",
				Role.ORG_PUBLISHER, username, "Description", "Full Name" )
				.build();

		arcPortalApi.userApi.addUser( newUserRequestModel );
	}

	private static void removeUserExample( ArcPortalApi arcPortalApi, String username )
	{
		arcPortalApi.userApi.deleteUser( username );
	}

	private static String uploadItemExample( ArcPortalApi arcPortalApi, String username )
	{
		File file = new File(Main.class.getClassLoader().getResource("example.csv").getFile());

		ItemUploadModel itemUploadModel = new ItemUploadModel(file, "CSV")
				.text("This is an example file")
				.title(UUID.randomUUID().toString().replace("-", ""))
				.url("www.example.com")
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

		return arcPortalApi.itemApi.uploadItem(itemUploadModel, username).id;
	}

	private static String publishItemExample( ArcPortalApi arcPortalApi, String id, String username,
											  String publishParameters )
	{
		ItemPublishModel itemPublishModel = new ItemPublishModel(id, "CSV", publishParameters);
		return arcPortalApi.itemApi.publishItem(itemPublishModel, username ).services.get(0).serviceItemId;
	}

	public static void shareItemExample( ArcPortalApi arcPortalApi, String id, String username, String groupId )
	{
		//ids is a comma seperated list of item ids, groups is a comma seperated list of groups to share with
		//in the example it's just one item for one group
		arcPortalApi.itemApi.shareItem( id, username, groupId );
	}

	private static void deleteItemExample( ArcPortalApi arcPortalApi, String id, String username )
	{
		arcPortalApi.itemApi.deleteItem( id, username );
	}

	private static String createGroupExample( ArcPortalApi arcPortalApi )
	{
		Group group = newGroup()
			.title( UUID.randomUUID().toString() )
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

		group = arcPortalApi.groupApi.createGroup( group ).group;

		logger.info( "Created Group {}", group.id );

		GroupUserAddResponse groupUserAddResponse = arcPortalApi.groupApi.addUsersToGroup( group, Arrays.asList( "david.grosso", "modibo" ) );

		arcPortalApi.groupApi.removeUsersFromGroup( group, Arrays.asList( "david.grosso" ) );

		return group.id;
	}

	private static void deleteGroupExample( ArcPortalApi arcPortalApi, String id )
	{
		GroupDeleteResponse groupDeleteResponse = arcPortalApi.groupApi.deleteGroup( id );
		logger.info( "Deleted Group {}", groupDeleteResponse.groupId );
	}
}
