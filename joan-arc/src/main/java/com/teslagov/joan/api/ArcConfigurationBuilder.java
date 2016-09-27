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

	private String portalContextPath;

	private int portalPort;

	private boolean portalUsingWebAdaptor;

	private String arcServerAdminUsername;

	private String arcServerAdminPassword;

	private String arcServerUrl;

	private String arcServerContextPath;

	private int arcServerPort;

	private boolean arcServerUsingWebAdaptor;

	private void assertNothingNull()
	{
		if ( portalAdminUsername == null || portalAdminPassword == null || portalUrl == null || portalContextPath == null )
		{
			throw new IllegalStateException("Null properties");
		}

		if ( arcServerAdminUsername == null || arcServerAdminPassword == null || arcServerUrl == null || arcServerContextPath == null )
		{
			throw new IllegalStateException("Null properties");
		}
	}

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

	public ArcConfigurationBuilder portalContextPath( String contextPath )
	{
		if (contextPath.startsWith("/")) contextPath = contextPath.substring(1);
		this.portalContextPath = contextPath;
		return this;
	}

	public ArcConfigurationBuilder portalPort( int portalPort )
	{
		this.portalPort = portalPort;
		return this;
	}

	public ArcConfigurationBuilder portalIsUsingWebAdaptor( boolean portalUsingWebAdaptor )
	{
		this.portalUsingWebAdaptor = portalUsingWebAdaptor;
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

	public ArcConfigurationBuilder arcServerContextPath( String contextPath )
	{
		if (contextPath.startsWith("/")) contextPath = contextPath.substring(1);
		this.arcServerContextPath = contextPath;
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

	public ArcConfigurationBuilder arcServerIsUsingWebAdaptor( boolean arcServerUsingWebAdaptor )
	{
		this.arcServerUsingWebAdaptor = arcServerUsingWebAdaptor;
		return this;
	}

	public ArcConfiguration build()
	{
		assertNothingNull();

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
			public String getPortalContextPath() {
				return portalContextPath;
			}

			@Override
			public int getPortalPort()
			{
				return portalPort;
			}

			@Override
			public boolean isPortalUsingWebAdaptor() {
				return portalUsingWebAdaptor;
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
			public String getArcServerContextPath() {
				return arcServerContextPath;
			}

			@Override
			public int getArcServerPort()
			{
				return arcServerPort;
			}

			@Override
			public boolean isArcServerUsingWebAdaptor() {
				return arcServerUsingWebAdaptor;
			}

			@Override
			public String getArcServerAdminApiPath()
			{
				return getArcServerUrl() + ":" + getArcServerPort() + "/arcgis/admin";
			}
		};
	}
}
