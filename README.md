# Joan
<figure>
<img src="https://upload.wikimedia.org/wikipedia/commons/3/39/Joan_of_arc_miniature_graded.jpg" width="200" alt="Joan of Arc"/>
<figcaption><a href="https://en.wikipedia.org/wiki/Joan_of_Arc">Joan</a> is a Java SDK that simplifies communicating with <a href="http://resources.arcgis.com/en/help/arcgis-rest-api/index.html">ArcGIS REST APIs</a>.</figcaption>
</figure>

## Usage
```groovy
compile com.teslagov:joan-arc:0.0.1 
```

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
    
HttpClient httpClient = TrustingHttpClientFactory.createVeryUnsafePortalHttpClient( arcConfiguration );
    
ArcApi arcApi = new ArcApi( httpClient, arcConfiguration );
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
```