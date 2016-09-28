package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcPortalConfiguration;

/**
 * @author Kevin Chen
 */
public class ArcPortalConfigurationBuilder {
	private String portalAdminUsername;

	private String portalAdminPassword;

	private String portalUrl;

	private String portalContextPath;

	private Integer portalPort;

	private Boolean portalUsingWebAdaptor;

	private boolean isNullOrEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	private void validateFields() {
		if (isNullOrEmpty(portalAdminUsername) || isNullOrEmpty(portalAdminPassword) || isNullOrEmpty(portalUrl)) {
			throw new IllegalStateException("Must provide username, password, and url.");
		}

		if (portalUsingWebAdaptor == Boolean.TRUE) {
			if (isNullOrEmpty(portalContextPath)) {
				throw new IllegalStateException("If you're hitting Portal via its Web Adaptor, you must provide a context path.");
			}
		} else {
			if (portalPort == null) {
				throw new IllegalStateException("If you're hitting Portal directly, you must provide a port number.");
			}
		}
	}

	public static ArcPortalConfigurationBuilder portalConfig() {
		return new ArcPortalConfigurationBuilder();
	}

	public ArcPortalConfigurationBuilder portalAdminUsername(String portalAdminUsername) {
		this.portalAdminUsername = portalAdminUsername;
		return this;
	}

	public ArcPortalConfigurationBuilder portalAdminPassword(String portalAdminPassword) {
		this.portalAdminPassword = portalAdminPassword;
		return this;
	}

	public ArcPortalConfigurationBuilder portalUrl(String portalUrl) {
		this.portalUrl = portalUrl;
		return this;
	}

	public ArcPortalConfigurationBuilder portalContextPath(String contextPath) {
		if (contextPath.startsWith("/")) contextPath = contextPath.substring(1);
		this.portalContextPath = contextPath;
		return this;
	}

	public ArcPortalConfigurationBuilder portalPort(int portalPort) {
		this.portalPort = portalPort;
		return this;
	}

	public ArcPortalConfigurationBuilder portalIsUsingWebAdaptor(boolean portalUsingWebAdaptor) {
		this.portalUsingWebAdaptor = portalUsingWebAdaptor;
		return this;
	}

	public ArcPortalConfiguration build() {

		validateFields();

		return new ArcPortalConfiguration() {
			@Override
			public String getPortalAdminUsername() {
				return portalAdminUsername;
			}

			@Override
			public String getPortalAdminPassword() {
				return portalAdminPassword;
			}

			@Override
			public String getPortalUrl() {
				return portalUrl;
			}

			@Override
			public String getPortalContextPath() {
				return portalContextPath;
			}

			@Override
			public int getPortalPort() {
				return portalPort;
			}

			@Override
			public boolean isPortalUsingWebAdaptor() {
				return portalUsingWebAdaptor;
			}

			@Override
			public String getPortalAdminApiPath() {
				if (!isPortalUsingWebAdaptor()) {
					return getPortalUrl() + ":" + getPortalPort() + "/arcgis/portaladmin";
				}
				return getPortalUrl() + "/" + getPortalContextPath() + "/portaladmin";
			}

			@Override
			public String getPortalSharingApiPath() {
				if (!isPortalUsingWebAdaptor()) {
					return getPortalUrl() + ":" + getPortalPort() + "/arcgis/sharing/rest";
				}
				return getPortalUrl() + "/" + getPortalContextPath() + "/sharing/rest";
			}
		};
	}
}
