package com.teslagov.joan.portal;

import com.teslagov.joan.ArcConfiguration;
import com.teslagov.joan.portal.group.Group;

/**
 * @author Kevin Chen
 */
public class PortalEndpointFactory
{
	private static String createCommunityPath( ArcConfiguration arcConfiguration )
	{
		return String.format( "%s/community", arcConfiguration.getPortalApiPath() );
	}

	private static String createGroupsUrl( ArcConfiguration arcConfiguration )
	{
		return createCommunityPath( arcConfiguration ) + "/groups";
	}

	public static String createCreateGroupPath( ArcConfiguration arcConfiguration )
	{
		return createCommunityPath( arcConfiguration ) + "/createGroup";
	}

	public static String createDeleteGroupPath( ArcConfiguration arcConfiguration, Group group )
	{
		return createGroupsUrl( arcConfiguration ) + "/" + group.id + "/delete";
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
