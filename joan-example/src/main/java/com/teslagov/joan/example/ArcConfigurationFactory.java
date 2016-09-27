package com.teslagov.joan.example;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.props.Properties;

/**
 * @author Kevin Chen
 */
public class ArcConfigurationFactory
{
	public static ArcConfiguration createArcConfiguration()
	{
		Properties properties = ArcPropertiesFactory.createArcProperties();
		return new ArcConfiguration()
		{
			@Override
			public String getPortalAdminUsername()
			{
				return properties.getString( ArcProperties.PORTAL_ADMIN_USERNAME );
			}

			@Override
			public String getPortalAdminPassword()
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
			public String getPortalContextPath() {
				return properties.getString( ArcProperties.PORTAL_CONTEXT_PATH );
			}

			@Override
			public boolean isPortalUsingWebAdaptor() {
				return properties.getBoolean( ArcProperties.PORTAL_IS_USING_WEB_ADAPTOR );
			}

			@Override
			public String getPortalAdminApiPath()
			{
				return getPortalUrl() + ":" + getPortalPort() + "/arcgis/portaladmin";
			}

			@Override
			public String getPortalSharingApiPath()
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
			public String getArcServerContextPath() {
				return properties.getString( ArcProperties.ARC_GIS_SERVER_CONTEXT_PATH );
			}

			@Override
			public boolean isArcServerUsingWebAdaptor() {
				return properties.getBoolean( ArcProperties.ARC_GIS_SERVER_IS_USING_WEB_ADAPTOR );
			}

			@Override
			public String getArcServerAdminApiPath()
			{
				return getArcServerUrl() + ":" + getArcServerPort() + "/arcgis/admin";
			}
		};
	}
}
