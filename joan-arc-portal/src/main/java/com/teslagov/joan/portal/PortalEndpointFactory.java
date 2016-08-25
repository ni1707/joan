package com.teslagov.joan.portal;

import com.teslagov.joan.core.ArcConfiguration;

/**
 * @author Kevin Chen
 */
public class PortalEndpointFactory
{
	public static final class PortalAdmin
	{
		public static class Security
		{
			private static String security( ArcConfiguration arcConfiguration )
			{
				return String.format( "%s/security", arcConfiguration.getPortalAdminApiPath() );
			}

			private static String users( ArcConfiguration arcConfiguration )
			{
				return security( arcConfiguration ) + "/users";
			}

			public static String createCreateUserPath( ArcConfiguration arcConfiguration )
			{
				return users( arcConfiguration ) + "/createUser";
			}
		}
	}
	private static String createCommunityPath( ArcConfiguration arcConfiguration )
	{
		return String.format( "%s/community", arcConfiguration.getPortalSharingApiPath() );
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

	public static String createAddUserToGroupPath( ArcConfiguration arcConfiguration, String groupID )
	{
		return createGroupUrl( arcConfiguration, groupID ) + "/addUsers";
	}

	public static String createRemoveUserToGroupPath( ArcConfiguration arcConfiguration, String groupID )
	{
		return createGroupUrl( arcConfiguration, groupID ) + "/removeUsers";
	}

	public static String createFetchUsersPath( ArcConfiguration arcConfiguration, String portalID )
	{
		return String.format( "%s/portals/%s/users", arcConfiguration.getPortalSharingApiPath(), portalID );
	}

	public static String createGenerateTokenPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalSharingApiPath() + "/generateToken";
	}

	public static String createGetPortalIDPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalSharingApiPath() + "/portals/self";
	}
}
