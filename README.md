# Joan
[Joan](https://en.wikipedia.org/wiki/Joan_of_Arc) is a **Java 8** SDK that simplifies communicating with 
[ArcGIS REST APIs](http://resources.arcgis.com/en/help/arcgis-rest-api/index.html).

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
ArcConfiguration arcConfiguration =
  arcConfig()
    .portalAdminUsername( "PORTAL_ADMIN_USERNAME" )
    .portalAdminPassword( "PORTAL_ADMIN_PASSWORD" )
    .portalUrl( "https://my.arcgis.portal.hostname" )
    .portalPort( 7443 )
    .arcServerAdminUsername( "ARC_GIS_SERVER_ADMIN_USERNAME" )
    .arcServerAdminPassword( "ARC_GIS_SERVER_ADMIN_PASSWORD" )
    .arcServerUrl( "https://my.arcgis.server.hostname" )
    .arcServerPort( 6443 )
    .build();
```

and your [Apache `HttpClient`](https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/), and you'd be on your way:

```java
ArcApi arcApi = new ArcApi( httpClient, arcConfiguration );
```

### Users API
#### Fetch User List
```java
// fetch first 100 users
List<User> users = arcApi.fetchUsers();

// fetch users 101 thru 150
List<User> users = arcApi.fetchUsers( 100, 50 );
```

#### Create User
```java
// create a user object
UserRequestModel newUserRequestModel = newUser( "Username", "Password123!", "example@example.com",
				Role.ORG_USER, "Account ID", Description", "Full Name" )
				.build();

// add user to ArcGIS portal
arcApi.addUserViaPortal( newUserRequestModel );
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
  .title( "House Stark" )
  .description( "House Stark of Winterfell is one of the oldest lines of Westerosi nobility, stretching back over 8000 years." )
  .snippet( "Jon Snow is the current King in the North" )
  .tag( "game of thrones" ).tag( "got" ).tag( "alive" )
  .phone( "Winterfell, The North" )
  .access( GroupAccess.PUBLIC )
  .sortField( GroupSortField.TITLE )
  .sortOrder( SortOrder.ASCENDING )
  .isViewOnly( true )
  .isInvitationOnly( false )
  .thumbnail( "" )
  .build();

group = arcApi.createGroup( group ).group;
```

#### Update Group
```java
group.title = "Roose Bolton Group";
group.description = "Weddings..."
group.snippet = "What goes around comes around"
arcApi.updateGroup( group );
```

#### Delete Group
```java
String groupID = group.id;

arcApi.deleteGroup( groupID );
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
response = arcApi.addUsersToGroup( group, usersToAdd );
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
response = arcApi.removeUsersFromGroup( group, usersToRemove );
response.allUsersRemoved() == true
response.notRemoved.size() == 1
response.notRemoved.contains( "stark.impostor" ) == true
```

### Items API
#### Upload Item
```java
    File file = new File("example.csv");

    //Required parameters are either File and Type or URL and Type
    //Any others are optional
    UploadItemModel itemUploadModel = new UploadItemModel(file, "CSV")
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
```
  
#### Publish Item
```java
    //This requires publisher or admin role
    PublishItemModel itemPublishModel = new PublishItemModel(id);
    arcApi.publishItem( itemPublishModel, username );
```

#### Share Item
```java
    //ids is a comma seperated list of item ids, groups is a comma seperated list of groups to share with
	//in the example it's just one item for one group
	arcApi.shareItem( id, username, groupId );
```

#### Delete Item
```java
    arcApi.deleteItem( id, username );
```

