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
			public String getUsername()
			{
				return properties.getString( ArcProperties.USERNAME );
			}

			@Override
			public String getPassword()
			{
				return properties.getString( ArcProperties.PASSWORD );
			}

			@Override
			public String getPortalUrl()
			{
				return properties.getString( ArcProperties.PORTAL_URL );
			}

			@Override
			public int getPort()
			{
				return properties.getInteger( ArcProperties.PORT );
			}
		};
	}
}
