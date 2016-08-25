package com.teslagov.joan.core;

/**
 * @author Kevin Chen
 */
public interface ArcConfiguration
{
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
}
