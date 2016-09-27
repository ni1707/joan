package com.teslagov.joan.core;

/**
 * Configuration for hitting ArcGIS Portal and Server (contains info about credentials, ports, and context paths).
 * <p>
 * NOTE: If you bypass the Web Adaptor (i.e., if you provide a port number in the URL) and go straight to the Portal or Server instance, then the context path is {@code /}
 * If you hit the Web Adaptor (i.e., if you DO NOT provide a port number in the URL), then the context path is the one the sysadmin chose.
 *
 * @author Kevin Chen
 */
public interface ArcConfiguration {
	/**
	 * Returns the username of a Portal admin.
	 *
	 * @return the username of a Portal admin.
	 */
	String getPortalAdminUsername();

	/**
	 * Returns the password of a Portal admin.
	 *
	 * @return the password of a Portal admin.
	 */
	String getPortalAdminPassword();

	/**
	 * Returns the url where Portal lives.
	 *
	 * @return the url where Portal lives.
	 */
	String getPortalUrl();

	/**
	 * Returns the context path of Portal.
	 *
	 * @return the context path of Portal.
	 */
	String getPortalContextPath();

	/**
	 * Returns the port on which Portal runs.
	 *
	 * @return the port on which Portal runs.
	 */
	int getPortalPort();

	/**
	 * Returns a {@code http://<host>:<port>/arcgis/portaladmin} URL to the ArcGIS Portal REST API.
	 *
	 * @return a {@code http://<host>:<port>/arcgis/portaladmin} URL to the ArcGIS Portal REST API.
	 */
	String getPortalAdminApiPath();

	/**
	 * Returns a {@code http://<host>:<port>/arcgis/sharing/rest} URL to the ArcGIS Portal REST API.
	 *
	 * @return a {@code http://<host>:<port>/arcgis/sharing/rest} URL to the ArcGIS Portal REST API.
	 */
	String getPortalSharingApiPath();

	/**
	 * Checks whether ArcGIS Portal will be hit via the WebAdaptor (e.g., {@code http://server/webAdaptorContextPath})
	 * or directly (e.g., {@code http://server:portNumber/arcgis}. Notice how the context path is {@code /arcgis} when
	 * we hit ArcGIS Portal directly. This is because internally ArcGIS Portal runs out of a Tomcat container on port
	 * 7443 with a default context path of {@code /arcgis}. By contrast, the WebAdaptor is hit without a port number and
	 * with the context path that the sysadmin chose; the WebAdaptor merely proxies calls to the API to the internal instance.
	 *
	 * @return true if the REST client will hit ArcGIS Portal directly or via the WebAdaptor for Portal.
	 */
	boolean isPortalUsingWebAdaptor();

	/**
	 * Returns the username of a ArcGIS for Server admin.
	 *
	 * @return the username of a ArcGIS for Server admin.
	 */
	String getArcServerAdminUsername();

	/**
	 * Returns the password of a ArcGIS for Server admin.
	 *
	 * @return the password of a ArcGIS for Server admin.
	 */
	String getArcServerAdminPassword();

	/**
	 * Returns the url where ArcGIS for Server lives.
	 *
	 * @return the url where ArcGIS for Server lives.
	 */
	String getArcServerUrl();

	/**
	 * Returns the context path of ArcGIS for Server.
	 *
	 * @return the context path of ArcGIS for Server.
	 */
	String getArcServerContextPath();

	/**
	 * Returns the port on which ArcGIS for Server runs.
	 *
	 * @return the port on which ArcGIS for Server runs.
	 */
	int getArcServerPort();

	/**
	 * Returns the URL to the ArcGIS Server Administration REST API, e.g., {@code http://server:port/arcgis/admin}
	 *
	 * @return the URL to the ArcGIS Server Administration REST API.
	 */
	String getArcServerAdminApiPath();

	/**
	 * Checks whether ArcGIS Server will be hit via the WebAdaptor (e.g., {@code http://server/webAdaptorContextPath})
	 * or directly (e.g., {@code http://server:portNumber/arcgis}. Notice how the context path is {@code /arcgis} when
	 * we hit ArcGIS Server directly. This is because internally ArcGIS Server runs out of a Tomcat container on port
	 * 6443 with a default context path of {@code /arcgis}. By contrast, the WebAdaptor is hit without a port number and
	 * with the context path that the sysadmin chose; the WebAdaptor merely proxies calls to the API to the internal instance.
	 *
	 * @return true if the REST client will hit ArcGIS Server directly or via the WebAdaptor for ArcGIS Server.
	 */
	boolean isArcServerUsingWebAdaptor();
}
