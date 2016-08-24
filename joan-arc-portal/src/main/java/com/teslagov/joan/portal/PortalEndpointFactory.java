package com.teslagov.joan.portal;

import com.teslagov.joan.ArcConfiguration;

/**
 * @author Kevin Chen
 */
public class PortalEndpointFactory
{
	public static String createFetchUsersPath( ArcConfiguration arcConfiguration, String portalID )
	{
		return String.format( "%s/portals/%s/users", arcConfiguration.getPortalApiPath(), portalID );
	}

	public static String createGenerateTokenPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalApiPath() + "/generateToken";
	}

	public static String createGetPortalIDPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalApiPath() + "/portals/self";
	}
}
