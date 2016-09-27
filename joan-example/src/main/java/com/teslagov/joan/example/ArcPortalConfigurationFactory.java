package com.teslagov.joan.example;

import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.props.Properties;

/**
 * @author Kevin Chen
 */
public class ArcPortalConfigurationFactory {
	public static ArcPortalConfiguration createArcConfiguration() {
		Properties properties = ArcPropertiesFactory.createArcProperties();
		return new ArcPortalConfiguration() {
			@Override
			public String getPortalAdminUsername() {
				return properties.getString(ArcProperties.PORTAL_ADMIN_USERNAME);
			}

			@Override
			public String getPortalAdminPassword() {
				return properties.getString(ArcProperties.PORTAL_ADMIN_PASSWORD);
			}

			@Override
			public String getPortalUrl() {
				return properties.getString(ArcProperties.PORTAL_URL);
			}

			@Override
			public int getPortalPort() {
				return properties.getInteger(ArcProperties.PORTAL_PORT);
			}

			@Override
			public String getPortalContextPath() {
				return properties.getString(ArcProperties.PORTAL_CONTEXT_PATH);
			}

			@Override
			public boolean isPortalUsingWebAdaptor() {
				return properties.getBoolean(ArcProperties.PORTAL_IS_USING_WEB_ADAPTOR);
			}

			@Override
			public String getPortalAdminApiPath() {
				return getPortalUrl() + ":" + getPortalPort() + "/arcgis/portaladmin";
			}

			@Override
			public String getPortalSharingApiPath() {
				return getPortalUrl() + ":" + getPortalPort() + "/arcgis/sharing/rest";
			}
		};
	}
}
