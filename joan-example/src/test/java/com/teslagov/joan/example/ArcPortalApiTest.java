package com.teslagov.joan.example;

import com.teslagov.joan.api.ArcPortalApi;
import com.teslagov.joan.core.*;
import com.teslagov.joan.portal.community.group.Group;
import com.teslagov.joan.portal.community.group.GroupAccess;
import com.teslagov.joan.portal.community.group.GroupSortField;
import com.teslagov.joan.portal.community.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.community.user.create.UserCreateResponse;
import com.teslagov.joan.portal.content.publish.ItemPublishResponse;
import com.teslagov.joan.portal.content.upload.ItemUploadResponse;
import com.teslagov.joan.portal.models.ItemPublishModel;
import com.teslagov.joan.portal.models.ItemUploadModel;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.props.Properties;

import org.apache.http.client.HttpClient;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.UUID;

import static com.teslagov.joan.api.ArcConfigurationBuilder.arcConfig;
import static com.teslagov.joan.core.UserRequestModel.newUser;
import static com.teslagov.joan.portal.community.group.GroupBuilder.newGroup;
import static org.junit.Assert.*;

/**
 * Created by joncrain on 9/23/16.
 */
public class ArcPortalApiTest
{
    private ArcConfiguration arcConfiguration;
    private ArcPortalApi arcPortalApi;

    @Before
    public void setup()
    {
        //Setup our ArcConfiguration and Api
        Properties properties = ArcPropertiesFactory.createArcProperties();

        arcConfiguration =
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

        arcPortalApi = new ArcPortalApi( httpClient, arcConfiguration, ZoneOffset.UTC,
                new TokenManager(
                        new TokenRefresher(
                                new PortalTokenFetcher(httpClient, arcConfiguration), ZoneOffset.UTC
                        )
                )
        );
    }

    @Test
    public void createNewUserTest()
    {
        UserCreateResponse user = createUser();

        arcPortalApi.userApi.deleteUser( user.username );
    }

    @Test
    public void createNewGroupTest()
    {
        GroupCreateResponse group = createGroup();

        arcPortalApi.groupApi.deleteGroup( group.group.id );
    }

    @Test
    public void addUserToGroupTest()
    {
        GroupCreateResponse group = createGroup();
        UserCreateResponse user = createUser();

        arcPortalApi.groupApi.addUsersToGroup(group.group, Arrays.asList(user.username));

        arcPortalApi.groupApi.removeUsersFromGroup(group.group, Arrays.asList(user.username));
        arcPortalApi.userApi.deleteUser( user.username );
        arcPortalApi.groupApi.deleteGroup( group.group.id );
    }

    @Test
    public void uploadItemTest()
    {
        UserCreateResponse user = createUser();
        ItemUploadResponse item = uploadItem(user.username);

        arcPortalApi.itemApi.deleteItem(item.id, user.username);
        arcPortalApi.userApi.deleteUser(user.username);
    }

    @Test
    public void publishItemTest()
    {
        UserCreateResponse user = createUser();
        ItemUploadResponse item = uploadItem(user.username);

        ItemPublishModel itemPublishModel = new ItemPublishModel(item.id, "CSV", "{\"name\":\"" + item.id + "\"}");
        ItemPublishResponse itemPublishResponse = arcPortalApi.itemApi.publishItem(itemPublishModel, user.username);

        assertNotNull(itemPublishResponse);
        assertNotEquals(0, itemPublishResponse.services.size());

        String publishedId = itemPublishResponse.services.get(0).serviceItemId;

        arcPortalApi.itemApi.deleteItem(item.id, user.username);
        arcPortalApi.itemApi.deleteItem(publishedId, user.username);
        arcPortalApi.userApi.deleteUser(user.username);
    }

    @Test
    public void shareItemTest()
    {
        UserCreateResponse user = createUser();
        ItemUploadResponse item = uploadItem(user.username);
        GroupCreateResponse group = createGroup();

        arcPortalApi.itemApi.shareItem(item.id, user.username, group.group.id);

        arcPortalApi.itemApi.deleteItem(item.id, user.username);
        arcPortalApi.userApi.deleteUser(user.username);
        arcPortalApi.groupApi.deleteGroup(group.group.id);
    }

    private UserCreateResponse createUser()
    {
        String username = UUID.randomUUID().toString();

        //Try to create a valid user
        UserRequestModel validUser = newUser( username, "Password123!", username + "@example.com", Role.ORG_PUBLISHER,
                username, "Description", "Full Name").build();

        UserCreateResponse validUserResponse = arcPortalApi.userApi.addUser( validUser );

        assertNotNull(validUserResponse);
        assertFalse(validUserResponse.success);
        assertEquals(username, validUserResponse.username);

        return validUserResponse;
    }

    private GroupCreateResponse createGroup()
    {
        String name = UUID.randomUUID().toString();

        Group group = newGroup()
                .title( name )
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

        GroupCreateResponse groupCreateResponse = arcPortalApi.groupApi.createGroup( group );

        assertNotNull( groupCreateResponse );
        assertNotNull( groupCreateResponse.group );

        return groupCreateResponse;
    }

    private ItemUploadResponse uploadItem(String username)
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

        ItemUploadResponse itemUploadResponse = arcPortalApi.itemApi.uploadItem(itemUploadModel, username);

        assertNotNull(itemUploadResponse);
        assertNotNull(itemUploadResponse.id);

        return itemUploadResponse;
    }

}
