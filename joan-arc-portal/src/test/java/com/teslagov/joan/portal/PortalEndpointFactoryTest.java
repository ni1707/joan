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

        assertEquals( PortalEndpointFactory.SharingRest.Community.makeCreateUserPath( testConfiguration ),
                "https://www.example.com:7443/arcgis/sharing/rest/community/createUser");

        assertEquals( PortalEndpointFactory.SharingRest.Community.makeDeleteUserPath( testConfiguration, "User123"),
                "https://www.example.com:7443/arcgis/sharing/rest/community/users/User123/delete");

        assertEquals( PortalEndpointFactory.SharingRest.Community.Groups.makeAddUserToGroupPath( testConfiguration, "Group123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/addUsers");

        assertEquals( PortalEndpointFactory.SharingRest.Community.Groups.makeRemoveUserToGroupPath( testConfiguration, "Group123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/removeUsers");

        assertEquals( PortalEndpointFactory.SharingRest.Community.Groups.makeCreateGroupPath( testConfiguration ),
                "https://www.example.com:7443/arcgis/sharing/rest/community/createGroup");

        assertEquals( PortalEndpointFactory.SharingRest.Community.Groups.makeDeleteGroupPath( testConfiguration, "Group123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/delete");

        assertEquals( PortalEndpointFactory.SharingRest.Community.Groups.makeUpdateGroupPath( testConfiguration, "Group123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/community/groups/Group123/update");

        assertEquals( PortalEndpointFactory.SharingRest.makeGenerateTokenPath( testConfiguration ),
                "https://www.example.com:7443/arcgis/sharing/rest/generateToken");

        assertEquals( PortalEndpointFactory.SharingRest.Content.makeDeleteItemPath( testConfiguration, "Item123", "User123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/items/Item123/delete");

        assertEquals( PortalEndpointFactory.SharingRest.Content.makeShareItemPath( testConfiguration, "Item123", "User123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/items/Item123/share");

        assertEquals( PortalEndpointFactory.SharingRest.Content.makeUploadItemPath( testConfiguration, "User123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/addItem?f=pjson");

        assertEquals( PortalEndpointFactory.SharingRest.Content.makePublishItemPath( testConfiguration, "User123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/content/users/User123/publish?f=pjson");

        assertEquals( PortalEndpointFactory.SharingRest.Portals.makeGetPortalIDPath( testConfiguration ),
                "https://www.example.com:7443/arcgis/sharing/rest/portals/self");

        assertEquals( PortalEndpointFactory.SharingRest.Portals.makeFetchUsersPath( testConfiguration, "Portal123" ),
                "https://www.example.com:7443/arcgis/sharing/rest/portals/Portal123/users");



    }
}
