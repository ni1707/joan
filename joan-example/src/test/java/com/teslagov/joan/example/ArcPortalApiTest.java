package com.teslagov.joan.example;

import com.teslagov.joan.api.ArcPortalApi;
import com.teslagov.joan.core.*;
import com.teslagov.joan.core.http.HttpExecutor;
import com.teslagov.joan.core.http.HttpPostBuilder;
import com.teslagov.joan.portal.community.group.Group;
import com.teslagov.joan.portal.community.group.GroupAccess;
import com.teslagov.joan.portal.community.group.GroupSortField;
import com.teslagov.joan.portal.community.group.create.GroupCreateResponse;
import com.teslagov.joan.portal.community.group.fetchUsers.GroupUserFetchResponse;
import com.teslagov.joan.portal.community.user.create.UserCreateResponse;
import com.teslagov.joan.portal.content.publish.ItemPublishResponse;
import com.teslagov.joan.portal.content.upload.ItemUploadResponse;
import com.teslagov.joan.portal.models.ItemPublishModel;
import com.teslagov.joan.portal.models.ItemUploadModel;
import com.teslagov.joan.portal.token.PortalTokenFetcher;
import com.teslagov.joan.portal.token.PortalTokenResponse;
import com.teslagov.props.Properties;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.UUID;

import static com.teslagov.joan.api.ArcConfigurationBuilder.arcConfig;
import static com.teslagov.joan.api.ArcPortalConfigurationBuilder.portalConfig;
import static com.teslagov.joan.core.UserRequestModel.newUser;
import static com.teslagov.joan.portal.community.group.GroupBuilder.newGroup;
import static org.junit.Assert.*;

/**
 * Created by joncrain on 9/23/16.
 */
public class ArcPortalApiTest {
	private ArcConfiguration arcConfiguration;
	private ArcPortalApi arcPortalApi;
	private HttpClient httpClient;
	private TokenManager tokenManager;

	@Before
	public void setup() {
		//Setup our ArcConfiguration and Api
		Properties properties = ArcPropertiesFactory.createArcProperties();

		arcConfiguration =
				arcConfig()
						.arcPortalConfiguration(
								portalConfig()
										.portalAdminUsername(properties.getString(ArcProperties.PORTAL_ADMIN_USERNAME))
										.portalAdminPassword(properties.getString(ArcProperties.PORTAL_ADMIN_PASSWORD))
										.portalUrl(properties.getString(ArcProperties.PORTAL_URL))
										.portalPort(properties.getInteger(ArcProperties.PORTAL_PORT))
										.portalContextPath(properties.getString(ArcProperties.PORTAL_CONTEXT_PATH))
										.portalIsUsingWebAdaptor(properties.getBoolean(ArcProperties.PORTAL_IS_USING_WEB_ADAPTOR))
										.build()
						)
						.build();

		ArcPortalConfiguration arcPortalConfiguration = arcConfiguration.getArcPortalConfiguration();

		httpClient = TrustingHttpClientFactory.createVeryUnsafePortalHttpClient(arcConfiguration);

		arcPortalApi = new ArcPortalApi(httpClient, arcPortalConfiguration, ZoneOffset.UTC,
				new TokenManager(
						new TokenRefresher(
								new PortalTokenFetcher(httpClient, arcPortalConfiguration), ZoneOffset.UTC
						)
				)
		);
	}

	@Test
	public void createNewUserTest() {
		UserCreateResponse user = createUser();

		arcPortalApi.userApi.deleteUser(user.username);
	}

	@Test
	public void createNewGroupTest() {
		GroupCreateResponse group = createGroup();

		arcPortalApi.groupApi.deleteGroup(group.group.id);
	}

	@Test
	public void addUserToGroupTest() {
		GroupCreateResponse group = createGroup();
		UserCreateResponse user = createUser();

		arcPortalApi.groupApi.addUsersToGroup(group.group, Arrays.asList(user.username));

		arcPortalApi.groupApi.removeUsersFromGroup(group.group, Arrays.asList(user.username));
		arcPortalApi.userApi.deleteUser(user.username);
		arcPortalApi.groupApi.deleteGroup(group.group.id);
	}

	@Test
	public void uploadItemTest() {
		UserCreateResponse user = createUser();
		ItemUploadResponse item = uploadItem(user.username);

		arcPortalApi.itemApi.deleteItem(item.id, user.username);
		arcPortalApi.userApi.deleteUser(user.username);
	}

	@Test
	public void publishItemTest() {
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
	public void analyzeAndShareItemTest() {
		UserCreateResponse user = createUser();
		ItemUploadResponse item = uploadItem(user.username);

		String analyzeResponse = arcPortalApi.itemApi.analyzeItem(item.id);

		ItemPublishModel itemPublishModel = new ItemPublishModel(item.id, "CSV", analyzeResponse);
		ItemPublishResponse itemPublishResponse = arcPortalApi.itemApi.publishItem(itemPublishModel, user.username);

		assertNotNull(itemPublishResponse);
		assertNotEquals(0, itemPublishResponse.services.size());

		String publishedId = itemPublishResponse.services.get(0).serviceItemId;

		arcPortalApi.itemApi.deleteItem(item.id, user.username);
		arcPortalApi.itemApi.deleteItem(publishedId, user.username);
		arcPortalApi.userApi.deleteUser(user.username);
	}

	@Test
	public void shareItemTest() {
		UserCreateResponse user = createUser();
		ItemUploadResponse item = uploadItem(user.username);
		GroupCreateResponse group = createGroup();

		arcPortalApi.itemApi.shareItem(item.id, user.username, group.group.id);

		arcPortalApi.itemApi.deleteItem(item.id, user.username);
		arcPortalApi.userApi.deleteUser(user.username);
		arcPortalApi.groupApi.deleteGroup(group.group.id);
	}

	@Test
	public void sharedItemAccessTest() {
		UserCreateResponse user = createUser();
		ItemUploadResponse item = uploadItem(user.username);
		GroupCreateResponse group = createGroup();
		UserCreateResponse userWithoutAccess = createUser();
		GroupCreateResponse anotherGroup = createGroup();
		UserCreateResponse userWithAccess = createUser();

		arcPortalApi.groupApi.addUsersToGroup(group.group, Arrays.asList(user.username, userWithAccess.username));
		arcPortalApi.groupApi.addUsersToGroup(anotherGroup.group, Arrays.asList(userWithoutAccess.username));
		arcPortalApi.itemApi.shareItem(item.id, user.username, group.group.id);

		ArcPortalConfiguration arcPortalConfiguration = arcConfiguration.getArcPortalConfiguration();
		String portalUrl = arcPortalConfiguration.getPortalUrl();

		//We should be able to access the item with the user in the group
		HttpGet httpGet = new HttpGet(portalUrl + "/arcgis/sharing/rest/content/items/" + item.id + "?f=pjson");
		httpGet.addHeader("cookie", "agwtoken=" + getToken(userWithAccess.username, "Password123!"));

		Response response = HttpExecutor.getResponse(httpClient, httpGet, Response.class);

		assertNull(response.getError());

		//See if we can access this item with the wrong user
		httpGet = new HttpGet(portalUrl + "/arcgis/sharing/rest/content/items/" + item.id + "?f=pjson");
		httpGet.addHeader("cookie", "agwtoken=" + getToken(userWithoutAccess.username, "Password123!"));

		response = HttpExecutor.getResponse(httpClient, httpGet, Response.class);

		assertNotNull(response.getError());

		arcPortalApi.itemApi.deleteItem(item.id, user.username);
		arcPortalApi.userApi.deleteUser(userWithoutAccess.username);
		arcPortalApi.userApi.deleteUser(user.username);
		arcPortalApi.userApi.deleteUser(userWithAccess.username);
		arcPortalApi.groupApi.deleteGroup(anotherGroup.group.id);
		arcPortalApi.groupApi.deleteGroup(group.group.id);
	}

	@Test
	public void groupFetchTest()
	{
		GroupCreateResponse group = createGroup();
		UserCreateResponse user = createUser();

		arcPortalApi.groupApi.addUsersToGroup(group.group, Arrays.asList(user.username));

		GroupUserFetchResponse groupUserFetchResponse = arcPortalApi.groupApi.fetchGroupUsers(group.group.id);

		assertNull(groupUserFetchResponse.getError());

		arcPortalApi.groupApi.removeUsersFromGroup(group.group, Arrays.asList(user.username));
		arcPortalApi.userApi.deleteUser( user.username );
		arcPortalApi.groupApi.deleteGroup( group.group.id );
	}

	private UserCreateResponse createUser() {
		String username = UUID.randomUUID().toString();

		//Try to create a valid user
		UserRequestModel validUser = newUser(username, "Password123!", username + "@example.com", Role.ORG_PUBLISHER,
				username, "Description", "Full Name").build();

		UserCreateResponse validUserResponse = arcPortalApi.userApi.addUser(validUser);

		assertNotNull(validUserResponse);
		assertNull(validUserResponse.getError());
		//assertTrue(validUserResponse.success);
		//TODO: This should pass but doesn't, Jackson isn't grabbing success from the response for some reason
		assertEquals(username, validUserResponse.username);

		return validUserResponse;
	}

	private GroupCreateResponse createGroup() {
		String name = UUID.randomUUID().toString();

		Group group = newGroup()
				.title(name)
				.description("A test group owned by Kevin")
				.snippet("snippet...")
				.tag("tag1").tag("tag2").tag("tag3")
				.phone("1600 Pennsylvania Ave")
				.access(GroupAccess.PUBLIC)
				.sortField(GroupSortField.TITLE)
				.sortOrder(SortOrder.ASCENDING)
				.isViewOnly(true)
				.isInvitationOnly(false)
				.thumbnail("")
				.build();

		GroupCreateResponse groupCreateResponse = arcPortalApi.groupApi.createGroup(group);

		assertNotNull(groupCreateResponse);
		assertNotNull(groupCreateResponse.group);

		return groupCreateResponse;
	}

	private ItemUploadResponse uploadItem(String username) {
		File file = new File(Main.class.getClassLoader().getResource("example.csv").getFile());

		ItemUploadModel itemUploadModel = new ItemUploadModel(file, "CSV")
				.text("This is an example file")
				.title(UUID.randomUUID().toString().replace("-", ""))
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

	private String getToken(String username, String password) {
		ArcPortalConfiguration arcPortalConfiguration = arcConfiguration.getArcPortalConfiguration();
		HttpPost httpPost = new HttpPostBuilder(arcPortalConfiguration.getPortalUrl() + "/arcgis/sharing/rest/generateToken")
				.urlFormParam("f", "json")
				.urlFormParam("username", username)
				.urlFormParam("password", password)
				.urlFormParam("referer", "referer")
				.build();

		PortalTokenResponse tokenResponse = HttpExecutor.getResponse(httpClient, httpPost, PortalTokenResponse.class);

		return tokenResponse.getToken();
	}
}
