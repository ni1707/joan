package com.teslagov.joan;

import com.teslagov.properties.PropertyEnum;

/**
 * @author Kevin Chen
 */
public enum ArcProperties implements PropertyEnum
{
	/**
	 * The username of a Portal admin.
	 */
	USERNAME( "username" ),

	/**
	 * The password of a Portal admin.
	 */
	PASSWORD( "password" ),

	/**
	 * The Portal URL, e.g., https://my.web.adaptor.url/arcgis
	 */
	PORTAL_URL( "portalUrl" ),

	/**
	 * The port Portal is running on.
	 */
	PORT( "port" ),

	/**
	 * The referer to use when communicating with Portal's REST API.
	 */
	REFERER( "referer" );

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
