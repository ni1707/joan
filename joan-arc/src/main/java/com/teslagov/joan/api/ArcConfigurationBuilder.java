package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;

/**
 * @author Kevin Chen
 */
public class ArcConfigurationBuilder
{
	private String portalAdminUsername;

	private String portalAdminPassword;

	private String portalUrl;

	private int portalPort;

	private String arcServerAdminUsername;

	private String arcServerAdminPassword;

	private String arcServerUrl;

	private int arcServerPort;

	public static ArcConfigurationBuilder arcConfig()
	{
		return new ArcConfigurationBuilder();
	}

	public ArcConfigurationBuilder portalAdminUsername( String portalAdminUsername )
	{
		this.portalAdminUsername = portalAdminUsername;
		return this;
	}

	public ArcConfigurationBuilder portalAdminPassword( String portalAdminPassword )
	{
		this.portalAdminPassword = portalAdminPassword;
		return this;
	}

	public ArcConfigurationBuilder portalUrl( String portalUrl )
	{
		this.portalUrl = portalUrl;
		return this;
	}

	public ArcConfigurationBuilder portalPort( int portalPort )
	{
		this.portalPort = portalPort;
		return this;
	}

	public ArcConfigurationBuilder arcServerAdminUsername( String arcServerAdminUsername )
	{
		this.arcServerAdminUsername = arcServerAdminUsername;
		return this;
	}

	public ArcConfigurationBuilder arcServerAdminPassword( String arcServerAdminPassword )
	{
		this.arcServerAdminPassword = arcServerAdminPassword;
		return this;
	}

	public ArcConfigurationBuilder arcServerUrl( String arcServerUrl )
	{
		this.arcServerUrl = arcServerUrl;
		return this;
	}

	public ArcConfigurationBuilder arcServerPort( int arcServerPort )
	{
		this.arcServerPort = arcServerPort;
		return this;
	}

	public ArcConfiguration build()
	{
		// TODO throw exception if any are null
		return new ArcConfiguration()
		{
			@Override
			public String getPortalAdminUsername()
			{
				return portalAdminUsername;
			}

			@Override
			public String getPortalAdminPassword()
			{
				return portalAdminPassword;
			}

			@Override
			public String getPortalUrl()
			{
				return portalUrl;
			}

			@Override
			public int getPortalPort()
			{
				return portalPort;
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
				return arcServerAdminUsername;
			}

			@Override
			public String getArcServerAdminPassword()
			{
				return arcServerAdminPassword;
			}

			@Override
			public String getArcServerUrl()
			{
				return arcServerUrl;
			}

			@Override
			public int getArcServerPort()
			{
				return arcServerPort;
			}

			@Override
			public String getArcServerAdminApiPath()
			{
				return getArcServerUrl() + ":" + getArcServerPort() + "/arcgis/admin";
			}
		};
	}
}
