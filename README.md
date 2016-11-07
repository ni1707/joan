# Joan
[Joan](https://en.wikipedia.org/wiki/Joan_of_Arc) is a **Java 8** SDK that simplifies communicating with 
[ArcGIS REST APIs](http://resources.arcgis.com/en/help/arcgis-rest-api/index.html).  
Most of these examples can be found in the ArcPortalApiTest.

## Publishing to Bintray
1. Make sure you're on the `master` branch: `git checkout master`
2. Pull latest code: `git pull`
3. Tag a new release: `git tag -a x.y.z -m "added new feature" && git push --tags`
4. Make sure you have a legit `gradle.properties` file
5. Clean and build new artifacts: `./gradlew clean build `
6. Publish to Bintray: `./gradlew bintrayUploadAllArtifacts`
7. Login to Bintray and press "Publish"

## Usage
### Dependency
In a Gradle buildscript
```groovy
    dependencies {
      compile 'com.teslagov:joan-arc:0.0.1'
    }
```

### Constructing an ArcApi
Then in Java, you'd create your configuration class
```java
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
```

and your [Apache `HttpClient`](https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/), and you'd be on your way:

```java
    cookieStore = new BasicCookieStore();
    
    httpClient = TrustingHttpClientFactory.createVeryUnsafePortalHttpClient(arcConfiguration, cookieStore);
    
    arcPortalApi = new ArcPortalApi(httpClient, arcPortalConfiguration, ZoneOffset.UTC,
        new TokenManager(
            new TokenRefresher(
                new PortalTokenFetcher(httpClient, arcPortalConfiguration), ZoneOffset.UTC
            )
        )
    );
```

### Users API
#### Fetch User List
```java
    // fetch first 100 users
    List<User> users = arcPortalApi.userApi.fetchUsers();
    
    // fetch users 101 thru 150
    List<User> users = arcPortalApi.userApi.fetchUsers( 100, 50 );
```

#### Create User
```java
//Create a user request object
    UserRequestModel validUser = newUser(username, "Password123!", username + "@example.com", Role.ORG_PUBLISHER,
        username, "Description", "Full Name").build();
    
    //Create a user
    UserCreateResponse validUserResponse = arcPortalApi.userApi.addUser(validUser);
```

#### Create Enterprise User(Without a password)
```java
UserAdminRequestModel userAdminRequestModel = new UserAdminRequestModel()
    .username(username)
    .firstname("Tester")
    .lastname("McGee")
    .role("org_publisher") //org_user, org_admin
    .email("testmc@example.com")
    .provider("enterprise")
    .f("pjson");

UserCreateResponse user = arcPortalApi.userApi.adminAddUser(userAdminRequestModel, cookieStore);
```

#### Delete User
```java
    // delete user from the ArcGIS portal
    arcApi.removeUser( "UserToDelete" );
```

### Groups API
#### Create Group
```java
    // upload a new group
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
```

#### Update Group
```java
    group.title = "Roose Bolton Group";
    group.description = "Weddings..."
    group.snippet = "What goes around comes around"
    arcPortalApi.groupApi.updateGroup( group );
```

#### Delete Group
```java
    String groupID = group.id;
    
    arcPortalApi.groupApi.deleteGroup( groupID );
```

#### Add Users To Group
```java
    GroupUserAddResponse response;
    List<String> usersToAdd;
    
    usersToAdd = Arrays.asList(
      "arya.stark",
      "jon.snow",
      "robb.stark",
      "talisa.stark",
      "catelyn.stark",
      "edmure.tully"
    );
    response = arcApi.groupApi.addUsersToGroup( group, usersToAdd );
    response.allUsersAdded() == true
```

#### Remove Users From Group
```java
    GroupUserRemoveResponse response;
    List<String> usersToRemove;
    
    usersToRemove = Arrays.asList(
      "robb.stark",
      "talisa.stark",
      "catelyn.stark",
      "edmure.tully",
      "stark.impostor"
    );
    response = arcApi.groupApi.removeUsersFromGroup( group, usersToRemove );
    response.allUsersRemoved() == true
    response.notRemoved.size() == 1
    response.notRemoved.contains( "stark.impostor" ) == true
```

#### Fetch Users In Group
```java
    GroupUserFetchResponse groupUserFetchResponse = arcPortalApi.groupApi.fetchGroupUsers( groupId );
    System.out.println(groupUserFetchResponse.users);
```

### Items API
#### Upload Item
```java
    File file = new File("example.csv");

    //Required parameters are either File and Type or URL and Type
    //Any others are optional
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
```

#### Publish Item
```java
    //This requires publisher or admin role
    ItemPublishModel itemPublishModel = new ItemPublishModel(item.id, "CSV", "{\"name\":\"" + item.id + "\"}");
    ItemPublishResponse itemPublishResponse = arcPortalApi.itemApi.publishItem(itemPublishModel, user.username);
```

#### Share Item
```java
    //ids is a comma seperated list of item ids, groups is a comma seperated list of groups to share with
    //in the example it's just one item for one group
    arcPortalApi.itemApi.shareItem(item.id, user.username, group.group.id);
```

#### Delete Item
```java
    arcPortalApi.itemApi.deleteItem(item.id, user.username);
```

