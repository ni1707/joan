package com.teslagov.joan;

import com.teslagov.properties.Properties;

/**
 * @author Kevin Chen
 */
public class ArcConfigurationFactory
{
	public static ArcConfiguration createArcConfiguration()
	{
		Properties properties = PropertiesFactory.createProperties();
		return new ArcConfiguration()
		{
			@Override
			public String getPortalUserName()
			{
				return properties.getString( ArcProperties.PORTAL_ADMIN_USERNAME );
			}

			@Override
			public String getPortalUserPassword()
			{
				return properties.getString( ArcProperties.PORTAL_ADMIN_PASSWORD );
			}

			@Override
			public String getPortalUrl()
			{
				return properties.getString( ArcProperties.PORTAL_URL );
			}

			@Override
			public int getPortalPort()
			{
				return properties.getInteger( ArcProperties.PORTAL_PORT );
			}

			@Override
			public String getPortalApiPath()
			{
				return getPortalUrl() + ":" + getPortalPort() + "/arcgis/sharing/rest";
			}

			@Override
			public String getArcServerAdminUsername()
			{
				return properties.getString( ArcProperties.ARC_GIS_SERVER_ADMIN_USERNAME );
			}

			@Override
			public String getArcServerAdminPassword()
			{
				return properties.getString( ArcProperties.ARC_GIS_SERVER_ADMIN_PASSWORD );
			}

			@Override
			public String getArcServerUrl()
			{
				return properties.getString( ArcProperties.ARC_GIS_SERVER_URL );
			}

			@Override
			public int getArcServerPort()
			{
				return properties.getInteger( ArcProperties.ARC_GIS_SERVER_PORT );
			}

			@Override
			public String getArcServerAdminApiPath()
			{
				return getArcServerUrl() + ":" + getArcServerPort() + "/arcgis/admin";
			}
		};
	}
}
