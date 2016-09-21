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
		}

		public static class Uploads
		{
			private static String uploads( ArcConfiguration arcConfiguration )
			{
				return String.format( "%s/uploads", arcConfiguration.getPortalAdminApiPath() );
			}

			public static String makeUploadPath( ArcConfiguration arcConfiguration )
			{
				return uploads( arcConfiguration ) + "/upload";
			}
		}
	}

	public static final class SharingRest
	{
		private static String sharingApi( ArcConfiguration arcConfiguration )
		{
			return arcConfiguration.getPortalSharingApiPath();
		}

		public static String makeGenerateTokenPath( ArcConfiguration arcConfiguration )
		{
			return sharingApi( arcConfiguration ) + "/generateToken";
		}

		public static class Portals
		{
			private static String portals( ArcConfiguration arcConfiguration )
			{
				return sharingApi(arcConfiguration) + "/portals";
			}

			public static String makeFetchUsersPath( ArcConfiguration arcConfiguration, String portalID )
			{
				return portals(arcConfiguration) + "/" + portalID + "/users";
			}

			public static String makeGetPortalIDPath( ArcConfiguration arcConfiguration )
			{
				return portals(arcConfiguration) + "/self";
			}
		}

		public static class Community
		{
			private static String communityPath( ArcConfiguration arcConfiguration )
			{
				return sharingApi( arcConfiguration ) + "/community";
			}

			public static String makeCreateUserPath( ArcConfiguration arcConfiguration )
			{
				return communityPath(arcConfiguration) + "/createUser";
			}

			public static String makeDeleteUserPath( ArcConfiguration arcConfiguration, String username )
			{
				return communityPath(arcConfiguration) + "/users/" + username + "/delete";
			}

			public static class Groups
			{
				private static String groupsUrl( ArcConfiguration arcConfiguration )
				{
					return communityPath( arcConfiguration ) + "/groups";
				}

				private static String groupUrl( ArcConfiguration arcConfiguration, String groupID )
				{
					return groupsUrl( arcConfiguration ) + "/" + groupID;
				}

				public static String makeCreateGroupPath( ArcConfiguration arcConfiguration )
				{
					return communityPath( arcConfiguration ) + "/createGroup";
				}

				public static String makeDeleteGroupPath( ArcConfiguration arcConfiguration, String groupID )
				{
					return groupUrl( arcConfiguration, groupID ) + "/delete";
				}

				public static String makeUpdateGroupPath( ArcConfiguration arcConfiguration, String groupID )
				{
					return groupUrl( arcConfiguration, groupID ) + "/update";
				}

				public static String makeAddUserToGroupPath( ArcConfiguration arcConfiguration, String groupID )
				{
					return groupUrl( arcConfiguration, groupID ) + "/addUsers";
				}

				public static String makeRemoveUserToGroupPath( ArcConfiguration arcConfiguration, String groupID )
				{
					return groupUrl( arcConfiguration, groupID ) + "/removeUsers";
				}
			}
		}

		public static class Content
		{
			private static String contentPath( ArcConfiguration arcConfiguration )
			{
				return sharingApi( arcConfiguration ) + "/content";
			}

			public static String makeUploadItemPath( ArcConfiguration arcConfiguration, String username )
			{
				return contentPath( arcConfiguration ) + "/users/" + username + "/addItem?f=pjson";
			}

			public static String makePublishItemPath( ArcConfiguration arcConfiguration, String username)
			{
				return contentPath( arcConfiguration ) + "/users/" + username + "/publish";
			}

			public static String makeDeleteItemPath( ArcConfiguration arcConfiguration, String id, String username)
			{
				return contentPath( arcConfiguration ) + "/users/" + username + "/items/" + id + "/delete";
			}
		}
	}

}
