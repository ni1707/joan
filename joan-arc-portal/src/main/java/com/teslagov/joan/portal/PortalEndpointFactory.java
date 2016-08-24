package com.teslagov.joan.portal;

import com.teslagov.joan.ArcConfiguration;

/**
 * @author Kevin Chen
 */
public class PortalEndpointFactory
{
	private static String createCommunityPath( ArcConfiguration arcConfiguration )
	{
		return String.format( "%s/community", arcConfiguration.getPortalApiPath() );
	}

	public static String createCreateGroupPath( ArcConfiguration arcConfiguration )
	{
		return createCommunityPath( arcConfiguration ) + "/createGroup";
	}

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
