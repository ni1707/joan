package com.teslagov.joan.portal;

import com.teslagov.joan.core.ArcPortalConfiguration;

/**
 * @author Kevin Chen
 */
public class PortalEndpointFactory {
	public static final class PortalAdmin {
		public static class Security {
			private static String security(ArcPortalConfiguration arcConfiguration) {
				return String.format("%s/security", arcConfiguration.getPortalAdminApiPath());
			}

			private static String users(ArcPortalConfiguration arcConfiguration) {
				return security(arcConfiguration) + "/users";
			}
		}

		public static class Uploads {
			private static String uploads(ArcPortalConfiguration arcConfiguration) {
				return String.format("%s/uploads", arcConfiguration.getPortalAdminApiPath());
			}

			public static String makeUploadPath(ArcPortalConfiguration arcConfiguration) {
				return uploads(arcConfiguration) + "/upload";
			}
		}
	}

	public static final class SharingRest {
		private static String sharingApi(ArcPortalConfiguration arcConfiguration) {
			return arcConfiguration.getPortalSharingApiPath();
		}

		public static String makeGenerateTokenPath(ArcPortalConfiguration arcConfiguration) {
			return sharingApi(arcConfiguration) + "/generateToken";
		}

		public static class Portals {
			private static String portals(ArcPortalConfiguration arcConfiguration) {
				return sharingApi(arcConfiguration) + "/portals";
			}

			public static String makeFetchUsersPath(ArcPortalConfiguration arcConfiguration, String portalID) {
				return portals(arcConfiguration) + "/" + portalID + "/users";
			}

			public static String makeGetPortalIDPath(ArcPortalConfiguration arcConfiguration) {
				return portals(arcConfiguration) + "/self";
			}
		}

		public static class Community {
			private static String communityPath(ArcPortalConfiguration arcConfiguration) {
				return sharingApi(arcConfiguration) + "/community";
			}

			public static String makeCreateUserPath(ArcPortalConfiguration arcConfiguration) {
				return communityPath(arcConfiguration) + "/createUser";
			}

			public static String makeDeleteUserPath(ArcPortalConfiguration arcConfiguration, String username) {
				return communityPath(arcConfiguration) + "/users/" + username + "/delete";
			}

			public static class Groups {
				private static String groupsUrl(ArcPortalConfiguration arcConfiguration) {
					return communityPath(arcConfiguration) + "/groups";
				}

				private static String groupUrl(ArcPortalConfiguration arcConfiguration, String groupID) {
					return groupsUrl(arcConfiguration) + "/" + groupID;
				}

				public static String makeCreateGroupPath(ArcPortalConfiguration arcConfiguration) {
					return communityPath(arcConfiguration) + "/createGroup";
				}

				public static String makeDeleteGroupPath(ArcPortalConfiguration arcConfiguration, String groupID) {
					return groupUrl(arcConfiguration, groupID) + "/delete";
				}

				public static String makeUpdateGroupPath(ArcPortalConfiguration arcConfiguration, String groupID) {
					return groupUrl(arcConfiguration, groupID) + "/update";
				}

				public static String makeAddUserToGroupPath(ArcPortalConfiguration arcConfiguration, String groupID) {
					return groupUrl(arcConfiguration, groupID) + "/addUsers";
				}

				public static String makeRemoveUserToGroupPath(ArcPortalConfiguration arcConfiguration, String groupID) {
					return groupUrl(arcConfiguration, groupID) + "/removeUsers";
				}
			}
		}

		public static class Content {
			private static String contentPath(ArcPortalConfiguration arcConfiguration) {
				return sharingApi(arcConfiguration) + "/content";
			}

			public static String makeUploadItemPath(ArcPortalConfiguration arcConfiguration, String username) {
				return contentPath(arcConfiguration) + "/users/" + username + "/addItem?f=pjson";
			}

			public static String makePublishItemPath(ArcPortalConfiguration arcConfiguration, String username) {
				return contentPath(arcConfiguration) + "/users/" + username + "/publish?f=pjson";
			}

			public static String makeDeleteItemPath(ArcPortalConfiguration arcConfiguration, String id, String username) {
				return contentPath(arcConfiguration) + "/users/" + username + "/items/" + id + "/delete";
			}

			public static String makeShareItemPath(ArcPortalConfiguration arcConfiguration, String id, String username) {
				return contentPath(arcConfiguration) + "/users/" + username + "/items/" + id + "/share";
			}

			public static String makeAnalyzeItemPath(ArcPortalConfiguration arcConfiguration) {
				return contentPath(arcConfiguration) + "/features/analyze";
			}

			public static String makeFetchItemPath(ArcPortalConfiguration arcConfiguration, String username) {
				return contentPath(arcConfiguration) + "/users/" + username + "?f=pjson";
			}
		}
	}

}
