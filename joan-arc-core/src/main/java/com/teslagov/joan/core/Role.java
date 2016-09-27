package com.teslagov.joan.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The roles in Portal and ArcGIS Server.
 *
 * @author Kevin Chen
 */
public enum Role {
	// TODO cannot get custom enum serialization working
	@JsonProperty("org_user")
	ORG_USER("org_user", "User (built-in)"),

	@JsonProperty("org_publisher")
	ORG_PUBLISHER("org_publisher", "Publisher (built-in)"),

	@JsonProperty("org_admin")
	ORG_ADMIN("org_admin", "Admin (built-in)");

	private final String name;

	private final String description;

	Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	// for deserialization
//	@JsonCreator
//	public static Role forValue( String value )
//	{
//		return Arrays.stream( Role.values() )
//			.filter( role -> role.getName().equals( value ) )
//			.findFirst()
//			.orElseThrow( () -> new IllegalArgumentException( "Could not find enum for name: " + value ) );
//	}
}
