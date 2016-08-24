package com.teslagov.joan.portal.group;

/**
 * @author Kevin Chen
 */
public enum GroupSortField
{
	TITLE( "title" ),
	OWNER( "owner" ),
	AVG_RATING( "avgrating" ),
	NUM_VIEWS( "numviews" ),
	CREATED( "created" ),
	MODIFIED( "modified" );

	private final String name;

	GroupSortField( String name )
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
