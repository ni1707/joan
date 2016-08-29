package com.teslagov.joan.portal.sharing.community.group;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

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

	@JsonCreator
	public static GroupAccess forValue( String value )
	{
		return Arrays.stream( GroupAccess.values() )
			.filter( ga -> ga.getName().equals( value ) )
			.findFirst()
			.orElseThrow( () -> new IllegalArgumentException( "Could not find enum for name: " + value ) );
	}
}
