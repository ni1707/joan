package com.teslagov.joan;

/**
 * @author Kevin Chen
 */
public enum Endpoint
{
	GENERATE_TOKEN( "/sharing/rest/generateToken" ),
	PORTAL_ID( "/sharing/rest/portals/self" );

	private String endpointPath;

	Endpoint( String endpointPath )
	{
		this.endpointPath = endpointPath;
	}

	Endpoint()
	{

	}

	public String getEndpointPath()
	{
		return endpointPath;
	}
}
