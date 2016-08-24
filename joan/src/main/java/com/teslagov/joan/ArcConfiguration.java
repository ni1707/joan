package com.teslagov.joan;

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
	String getPortalUserName();

	/**
	 * Returns the password of a Portal admin.
	 *
	 * @return the password of a Portal admin.
	 */
	String getPortalUserPassword();

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
	 * Returns the username of a ArcGIS for Server admin.
	 *
	 * @return the username of a ArcGIS for Server admin.
	 */
	String getArcGISServerUserName();

	/**
	 * Returns the password of a ArcGIS for Server admin.
	 *
	 * @return the password of a ArcGIS for Server admin.
	 */
	String getArcGISUserPassword();

	/**
	 * Returns the url where ArcGIS for Server lives.
	 *
	 * @return the url where ArcGIS for Server lives.
	 */
	String getArcGISUrl();

	/**
	 * Returns the port on which ArcGIS for Server runs.
	 *
	 * @return the port on which ArcGIS for Server runs.
	 */
	int getArcGISPort();
}
