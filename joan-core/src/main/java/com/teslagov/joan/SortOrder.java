package com.teslagov.joan;

/**
 * @author Kevin Chen
 */
public enum SortOrder
{
	ASCENDING( "asc" ),
	DESCENDING( "desc" );

	private final String name;

	SortOrder( String name )
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
