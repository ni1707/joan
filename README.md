# Joan
[Joan](https://en.wikipedia.org/wiki/Joan_of_Arc) is a Java SDK that simplifies communicating with 
[ArcGIS REST APIs](http://resources.arcgis.com/en/help/arcgis-rest-api/index.html).

## Usage
In a Gradle buildscript
```groovy
dependencies {
  compile 'com.teslagov:joan-arc:0.0.1'
}
```

Then in Java, you'd create your configuration class
```java
ArcConfiguration arcConfiguration =
  arcConfig()
    .portalAdminUsername( "PORTAL_ADMIN_USERNAME" )
    .portalAdminPassword( "PORTAL_ADMIN_PASSWORD" )
    .portalUrl( "PORTAL_URL" )
    .portalPort( "PORTAL_PORT" )
    .arcServerAdminUsername( "ARC_GIS_SERVER_ADMIN_USERNAME" )
    .arcServerAdminPassword( "ARC_GIS_SERVER_ADMIN_PASSWORD" )
    .arcServerUrl( "ARC_GIS_SERVER_URL" )
    .arcServerPort( "ARC_GIS_SERVER_PORT" )
    .build();
```

and your Apache `HttpClient`, and you'd be on your way:

```java
ArcApi arcApi = new ArcApi( httpClient, arcConfiguration );

// fetch first 100 users
List<User> users = arcApi.fetchUsers();

// fetch users 101 thru 150
List<User> users = arcApi.fetchUsers( 100, 50 );

// upload a new group
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

group = arcApi.createGroup( group ).group;

String groupID = group.id;

arcApi.deleteGroup( groupID );
```