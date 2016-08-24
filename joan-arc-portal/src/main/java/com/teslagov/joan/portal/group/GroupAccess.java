package com.teslagov.joan.portal.group;

/**
 * @author Kevin Chen
 */
public enum GroupAccess
{
	PRIVATE( "private" ),
	ORGANIZATION( "org" ),
	PUBLIC( "public" );

	private final String name;

	GroupAccess( String name )
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
