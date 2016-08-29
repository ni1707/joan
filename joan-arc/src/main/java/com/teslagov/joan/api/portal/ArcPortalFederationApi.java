package com.teslagov.joan.api.portal;

import com.teslagov.joan.api.AbstractArcRestApi;
import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.TokenManager;
import com.teslagov.joan.portal.admin.federation.ServerRole;
import org.apache.http.client.HttpClient;

import java.time.ZoneOffset;

/**
 * @author Kevin Chen
 */
public class ArcPortalFederationApi extends AbstractArcRestApi
{
	public ArcPortalFederationApi(
		HttpClient httpClient,
		ArcConfiguration arcConfiguration,
		ZoneOffset zoneOffset,
		TokenManager tokenManager,
		String apiName
	)
	{
		super( httpClient, arcConfiguration, zoneOffset, tokenManager, apiName );
	}

	/**
	 * This operation enables ArcGIS Servers to be federated with Portal for ArcGIS.
	 * Hits /arcgis/portaladmin/federation/servers/federate
	 *
	 * @param url      The URL of the GIS server used by external users when accessing the ArcGIS Server site. If the site includes the Web Adaptor,
	 *                 the URL includes the Web Adaptor address, for example, https://webadaptor.domain.com/arcgis. If you've added ArcGIS Server to
	 *                 your organization's reverse proxy server, the URL is the reverse proxy server address (for example,
	 *                 https://reverseproxy.domain.com/myorg).
	 * @param adminUrl The URL used for accessing ArcGIS Server when performing administrative operations on the internal network, for example,
	 *                 https://gisserver.domain.com:6443/arcgis.
	 * @param username The username of the primary site administrator account that was used to initially log in to Manager and administer ArcGIS
	 *                 Server. If this account is disabled, you'll need to re-enable it.
	 * @param password The password of the primary site administrator account.
	 */
	public void federateServers(
		String url,
		String adminUrl,
		String username,
		String password
	)
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * This resource returns information about the ArcGIS Servers registered with Portal for ArcGIS.
	 * Hits /arcgis/portaladmin/federation
	 */
	public void getFederationInfo()
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * This resource returns detailed information about the ArcGIS Servers registered with Portal for ArcGIS, such as the ID of the server, name of
	 * the server, ArcGIS Web Adaptor URL, administration URL, and if the server is set as a hosting server.
	 * Hits /arcgis/portaladmin/federation/servers
	 */
	public void getFederatedServers()
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * This operation unfederates an ArcGIS Server from Portal for ArcGIS.
	 * Hits /arcgis/portaladmin/federation/servers/<serverid>/unfederate
	 *
	 * @param serverID The server ID.
	 */
	public void unfederateServer( String serverID )
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * This operation allows you to set an ArcGIS Server federated with Portal for ArcGIS as the hosting server or to enforce fine-grained access
	 * control to a federated server. You can also remove hosting server status from an ArcGIS Server. You can also remove hosting server status from
	 * an ArcGIS Server. To set a hosting server, an enterprise geodatabase must be registered as a managed database with the ArcGIS Server.
	 * Hits /arcgis/portaladmin/federation/servers/<serverid>/update
	 *
	 * @param serverID   The server ID
	 * @param serverRole Whether the server is a hosting server for the portal, a federated server, or a server with restricted access to publishing.
	 */
	public void updateServer( String serverID, ServerRole serverRole )
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * This operation provides status information about a specific ArcGIS Server federated with Portal for ArcGIS.
	 * Hits /arcgis/portaladmin/federation/servers/<serverid>/validate
	 *
	 * @param serverID The server ID.
	 */
	public void validateServer( String serverID )
	{
		throw new UnsupportedOperationException( "" );
	}

	/**
	 * This operation returns information on the status of ArcGIS Servers registered with Portal for ArcGIS.
	 * Hits /arcgis/portaladmin/federation/servers/validate
	 */
	public void validateServers()
	{
		throw new UnsupportedOperationException( "" );
	}
}
