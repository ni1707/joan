package com.teslagov.joan;

/**
 * @author Kevin Chen
 */
public enum Endpoint
{
	PORTAL_ID( "/sharing/rest/portals/self" ),
	GENERATE_TOKEN( "/sharing/rest/generateToken" );

	private final String endpointPath;

	Endpoint( String endpointPath )
	{
		this.endpointPath = endpointPath;
	}

	public String getEndpointPath()
	{
		return endpointPath;
	}
}
