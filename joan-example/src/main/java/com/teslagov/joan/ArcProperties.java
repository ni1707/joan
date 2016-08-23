package com.teslagov.joan;

import com.teslagov.properties.PropertyEnum;

/**
 * @author Kevin Chen
 */
public enum ArcProperties implements PropertyEnum
{
	USERNAME( "username" ),
	PASSWORD( "password" ),
	PORTAL_URL( "portalUrl" ),
	PORT( "port" );

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
