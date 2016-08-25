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

	private static String createGroupsUrl( ArcConfiguration arcConfiguration )
	{
		return createCommunityPath( arcConfiguration ) + "/groups";
	}

	private static String createGroupUrl( ArcConfiguration arcConfiguration, String groupID )
	{
		return createGroupsUrl( arcConfiguration ) + "/" + groupID;
	}

	public static String createCreateGroupPath( ArcConfiguration arcConfiguration )
	{
		return createCommunityPath( arcConfiguration ) + "/createGroup";
	}

	public static String createDeleteGroupPath( ArcConfiguration arcConfiguration, String groupID )
	{
		return createGroupUrl( arcConfiguration, groupID ) + "/delete";
	}

	public static String createUpdateGroupPath( ArcConfiguration arcConfiguration, String groupID )
	{
		return createGroupUrl( arcConfiguration, groupID ) + "/update";
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
