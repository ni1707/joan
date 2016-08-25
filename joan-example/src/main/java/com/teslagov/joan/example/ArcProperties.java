package com.teslagov.joan.example;

import com.teslagov.properties.PropertyEnum;

/**
 * @author Kevin Chen
 */
public enum ArcProperties implements PropertyEnum
{
	/**
	 * The username of a Portal admin.
	 */
	PORTAL_ADMIN_USERNAME( "portalAdminUsername" ),

	/**
	 * The password of a Portal admin.
	 */
	PORTAL_ADMIN_PASSWORD( "portalAdminPassword" ),

	/**
	 * The Portal URL, e.g., https://my.web.adaptor.url/arcgis
	 */
	PORTAL_URL( "portalUrl" ),

	/**
	 * The port Portal is running on.
	 */
	PORTAL_PORT( "portalPort" ),

	/**
	 * The username of a ArcGIS Server admin.
	 */
	ARC_GIS_SERVER_ADMIN_USERNAME( "serverAdminUsername" ),

	/**
	 * The password of a ArcGIS Server admin.
	 */
	ARC_GIS_SERVER_ADMIN_PASSWORD( "serverAdminPassword" ),

	/**
	 * The URL for ArcGIS Server.
	 */
	ARC_GIS_SERVER_URL( "serverUrl" ),

	/**
	 * The port for ArcGIS Server.
	 */
	ARC_GIS_SERVER_PORT( "serverPort" );

	private final String propertyName;

	ArcProperties( String propertyName )
	{
		this.propertyName = propertyName;
	}

	@Override
	public String getPropertyName()
	{
		return propertyName;
	}
}
