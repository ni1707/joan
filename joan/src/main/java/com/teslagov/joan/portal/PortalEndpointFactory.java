package com.teslagov.joan.portal;

import com.teslagov.joan.ArcConfiguration;

/**
 * @author Kevin Chen
 */
public class PortalEndpointFactory
{
	public static String createCreateUserPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalUrl() + "/portaladmin/security/users/createUser";
	}

	public static String createGenerateTokenPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalUrl() + "/sharing/rest/generateToken";
	}

	public static String createGetPortalIDPath( ArcConfiguration arcConfiguration )
	{
		return arcConfiguration.getPortalUrl() + "/sharing/rest/portals/self";
	}
}
