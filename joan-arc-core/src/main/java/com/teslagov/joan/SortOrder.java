package com.teslagov.joan;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

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

	@JsonCreator
	public static SortOrder forValue( String value )
	{
		return Arrays.stream( SortOrder.values() )
			.filter( so -> so.getName().equals( value ) )
			.findFirst()
			.orElseThrow( () -> new IllegalArgumentException( "Could not find enum for name: " + value ) );
	}
}
