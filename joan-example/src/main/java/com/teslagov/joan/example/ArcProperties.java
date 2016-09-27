package com.teslagov.joan.example;

import com.teslagov.props.PropertyEnum;

/**
 * @author Kevin Chen
 */
public enum ArcProperties implements PropertyEnum {
	/**
	 * The username of a Portal admin.
	 */
	PORTAL_ADMIN_USERNAME("portalAdminUsername"),

	/**
	 * The password of a Portal admin.
	 */
	PORTAL_ADMIN_PASSWORD("portalAdminPassword"),

	/**
	 * The Portal URL, e.g., https://my.web.adaptor.url/arcgis
	 */
	PORTAL_URL("portalUrl"),

	/**
	 * The port Portal is running on.
	 */
	PORTAL_PORT("portalPort"),

	/**
	 * The context path for Portal, if it's not being hit via its WebAdaptor.
	 */
	PORTAL_CONTEXT_PATH("portalContextPath"),

	/**
	 * Whether Portal REST APIs should be hit via WebAdaptor or directly.
	 */
	PORTAL_IS_USING_WEB_ADAPTOR("portalIsUsingWebAdaptor"),

	/**
	 * The username of a ArcGIS Server admin.
	 */
	ARC_GIS_SERVER_ADMIN_USERNAME("serverAdminUsername"),

	/**
	 * The password of a ArcGIS Server admin.
	 */
	ARC_GIS_SERVER_ADMIN_PASSWORD("serverAdminPassword"),

	/**
	 * The URL for ArcGIS Server.
	 */
	ARC_GIS_SERVER_URL("serverUrl"),

	/**
	 * The port for ArcGIS Server.
	 */
	ARC_GIS_SERVER_PORT("serverPort"),

	/**
	 * The context path for ArcGIS Server, if it's not being hit via its WebAdaptor.
	 */
	ARC_GIS_SERVER_CONTEXT_PATH("serverContextPath"),

	/**
	 * Whether ArcGIS Server REST APIs should be hit via WebAdaptor or directly.
	 */
	ARC_GIS_SERVER_IS_USING_WEB_ADAPTOR("serverIsUsingWebAdaptor");

	private final String propertyName;

	ArcProperties(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String getPropertyName() {
		return propertyName;
	}
}
