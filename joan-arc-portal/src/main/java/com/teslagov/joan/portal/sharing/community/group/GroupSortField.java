package com.teslagov.joan.portal.sharing.community.group;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

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

	@JsonCreator
	public static GroupSortField forValue( String value )
	{
		return Arrays.stream( GroupSortField.values() )
			.filter( gsf -> gsf.getName().equals( value ) )
			.findFirst()
			.orElseThrow( () -> new IllegalArgumentException( "Could not find enum for name: " + value ) );
	}
}
