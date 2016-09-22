package com.teslagov.joan.portal;

import com.teslagov.joan.core.ArcConfiguration;

/**
 * Created by joncrain on 9/22/16.
 */
public class TestArcConfiguration implements ArcConfiguration
{
    public String getPortalAdminUsername() { return "admin"; }
    public String getPortalAdminPassword() { return "password"; }
    public String getPortalUrl() { return "https://www.example.com/arcgis"; }
    public int getPortalPort() { return 7443; }
    public String getPortalAdminApiPath() { return "https://www.example.com:7443/arcgis/portaladmin"; }
    public String getPortalSharingApiPath() { return "https://www.example.com:7443/arcgis/sharing/rest"; }
    public String getArcServerAdminUsername() { return "admin"; }
    public String getArcServerAdminPassword() { return "password"; }
    public String getArcServerUrl() { return "https://www.example.com/arcgis"; }
    public int getArcServerPort() { return 6443; }
    public String getArcServerAdminApiPath() { return "https://www.example.com:6443/arcgis/admin"; }
}
