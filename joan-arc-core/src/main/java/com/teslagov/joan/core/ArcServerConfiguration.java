package com.teslagov.joan.core;

/**
 * @author Kevin Chen
 */
public interface ArcServerConfiguration {
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
