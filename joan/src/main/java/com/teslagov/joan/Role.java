package com.teslagov.joan;

/**
 * The roles in Portal and ArcGIS Server.
 *
 * @author Kevin Chen
 */
public enum Role
{
	ORG_USER( "org_user", "User (built-in)" ),
	ORG_PUBLISHER( "org_publisher", "Publisher (built-in)" ),
	ORG_ADMIN( "org_admin", "Admin (built-in)" );

	private final String name;

	private final String description;

	Role( String name, String description )
	{
		this.name = name;
		this.description = description;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}
}
