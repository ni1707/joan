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
}
