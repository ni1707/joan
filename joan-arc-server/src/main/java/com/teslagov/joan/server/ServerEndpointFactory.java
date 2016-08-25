package com.teslagov.joan.server;

import com.teslagov.joan.ArcConfiguration;

/**
 * @author Kevin Chen
 */
public class ServerEndpointFactory
{
	public static String createGenerateTokenEndpoint( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getArcServerAdminApiPath() + "/generateToken";
	}

	public static class SecurityEndpointFactory
	{
		private static String createSecurityPath( ArcConfiguration arcConfiguration )
		{
			return arcConfiguration.getArcServerAdminApiPath() + "/security";
		}

		public static class UserEndpointFactory
		{
			private static String createUserPath( ArcConfiguration arcConfiguration )
			{
				return createSecurityPath( arcConfiguration ) + "/users";
			}

			public static String createAddUserPath( ArcConfiguration arcConfiguration )
			{
				return createUserPath( arcConfiguration ) + "/add";
			}
		}
	}
}
