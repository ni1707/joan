package com.teslagov.joan.portal;

import com.teslagov.joan.core.ArcConfiguration;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by joncrain on 9/22/16.
 */
public class PortalEndpointFactoryTest
{
    @Test
    public void testPortalEndpointFactoryUnitTest()
    {
        ArcConfiguration testConfiguration = new TestArcConfiguration();

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/createUser",
                PortalEndpointFactory.SharingRest.Community.makeCreateUserPath( testConfiguration ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/users/User123/delete",
                PortalEndpointFactory.SharingRest.Community.makeDeleteUserPath( testConfiguration, "User123") );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/addUsers",
                PortalEndpointFactory.SharingRest.Community.Groups.makeAddUserToGroupPath( testConfiguration, "Group123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/removeUsers",
                PortalEndpointFactory.SharingRest.Community.Groups.makeRemoveUserToGroupPath( testConfiguration, "Group123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/createGroup",
                PortalEndpointFactory.SharingRest.Community.Groups.makeCreateGroupPath( testConfiguration ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/delete",
                PortalEndpointFactory.SharingRest.Community.Groups.makeDeleteGroupPath( testConfiguration, "Group123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/update",
                PortalEndpointFactory.SharingRest.Community.Groups.makeUpdateGroupPath( testConfiguration, "Group123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/generateToken",
                PortalEndpointFactory.SharingRest.makeGenerateTokenPath( testConfiguration ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/items/Item123/delete",
                PortalEndpointFactory.SharingRest.Content.makeDeleteItemPath( testConfiguration, "Item123", "User123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/items/Item123/share",
                PortalEndpointFactory.SharingRest.Content.makeShareItemPath( testConfiguration, "Item123", "User123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/addItem?f=pjson",
                PortalEndpointFactory.SharingRest.Content.makeUploadItemPath( testConfiguration, "User123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/publish?f=pjson",
                PortalEndpointFactory.SharingRest.Content.makePublishItemPath( testConfiguration, "User123" ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/portals/self",
                PortalEndpointFactory.SharingRest.Portals.makeGetPortalIDPath( testConfiguration ) );

        assertEquals( "https://www.example.com:7443/arcgis/sharing/rest/portals/Portal123/users",
                PortalEndpointFactory.SharingRest.Portals.makeFetchUsersPath( testConfiguration, "Portal123" ) );



    }
}
