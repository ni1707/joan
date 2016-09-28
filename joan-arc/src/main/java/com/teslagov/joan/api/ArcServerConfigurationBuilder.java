package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcServerConfiguration;

/**
 * @author Kevin Chen
 */
public class ArcServerConfigurationBuilder {

	private String arcServerAdminUsername;

	private String arcServerAdminPassword;

	private String arcServerUrl;

	private String arcServerContextPath;

	private Integer arcServerPort;

	private Boolean arcServerUsingWebAdaptor;

	private boolean isNullOrEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	private void validateFields() {
		if (isNullOrEmpty(arcServerAdminUsername) || isNullOrEmpty(arcServerAdminPassword) || isNullOrEmpty(arcServerUrl)) {
			throw new IllegalStateException("Must provide username, password, and url.");
		}

		if (arcServerUsingWebAdaptor == Boolean.TRUE) {
			if (isNullOrEmpty(arcServerContextPath)) {
				throw new IllegalStateException("If you're hitting ArcGIS Server via its Web Adaptor, you must provide a context path.");
			}
		} else {
			if (arcServerPort == null) {
				throw new IllegalStateException("If you're hitting ArcGIS Server directly, you must provide a port number.");
			}
		}
	}

	public ArcServerConfigurationBuilder arcServerAdminUsername(String arcServerAdminUsername) {
		this.arcServerAdminUsername = arcServerAdminUsername;
		return this;
	}

	public ArcServerConfigurationBuilder arcServerAdminPassword(String arcServerAdminPassword) {
		this.arcServerAdminPassword = arcServerAdminPassword;
		return this;
	}

	public ArcServerConfigurationBuilder arcServerContextPath(String contextPath) {
		if (contextPath.startsWith("/")) contextPath = contextPath.substring(1);
		this.arcServerContextPath = contextPath;
		return this;
	}

	public ArcServerConfigurationBuilder arcServerUrl(String arcServerUrl) {
		this.arcServerUrl = arcServerUrl;
		return this;
	}

	public ArcServerConfigurationBuilder arcServerPort(int arcServerPort) {
		this.arcServerPort = arcServerPort;
		return this;
	}

	public ArcServerConfigurationBuilder arcServerIsUsingWebAdaptor(boolean arcServerUsingWebAdaptor) {
		this.arcServerUsingWebAdaptor = arcServerUsingWebAdaptor;
		return this;
	}

	public ArcServerConfiguration build() {

		validateFields();

		return new ArcServerConfiguration() {
			@Override
			public String getArcServerAdminUsername() {
				return arcServerAdminUsername;
			}

			@Override
			public String getArcServerAdminPassword() {
				return arcServerAdminPassword;
			}

			@Override
			public String getArcServerUrl() {
				return arcServerUrl;
			}

			@Override
			public String getArcServerContextPath() {
				return arcServerContextPath;
			}

			@Override
			public int getArcServerPort() {
				return arcServerPort;
			}

			@Override
			public boolean isArcServerUsingWebAdaptor() {
				return arcServerUsingWebAdaptor;
			}

			@Override
			public String getArcServerAdminApiPath() {
				if (!isArcServerUsingWebAdaptor()) {
					return getArcServerUrl() + ":" + getArcServerPort() + "/arcgis/admin";
				}
				return getArcServerUrl() + "/" + getArcServerContextPath() + "/admin";
			}
		};
	}
}
