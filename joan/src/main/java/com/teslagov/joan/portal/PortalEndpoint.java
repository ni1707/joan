package com.teslagov.joan.portal;

/**
 * @author Kevin Chen
 */
public enum PortalEndpoint
{
	GENERATE_TOKEN( "/sharing/rest/generateToken" ),
	PORTAL_ID( "/sharing/rest/portals/self" );

	private String endpointPath;

	PortalEndpoint( String endpointPath )
	{
		this.endpointPath = endpointPath;
	}

	PortalEndpoint()
	{

	}

	public String getEndpointPath()
	{
		return endpointPath;
	}
}
