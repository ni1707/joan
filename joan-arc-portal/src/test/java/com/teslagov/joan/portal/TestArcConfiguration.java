package com.teslagov.joan.portal;

import com.teslagov.joan.core.ArcConfiguration;

/**
 * Created by joncrain on 9/22/16.
 */
public class TestArcConfiguration implements ArcConfiguration {

	@Override
	public String getPortalAdminUsername() {
		return "admin";
	}

	@Override
	public String getPortalAdminPassword() {
		return "password";
	}

	@Override
	public String getPortalUrl() {
		return "https://www.example.com/" + getPortalContextPath();
	}

	@Override
	public String getPortalContextPath() {
		return "arcgis";
	}

	@Override
	public int getPortalPort() {
		return 7443;
	}

	@Override
	public boolean isPortalUsingWebAdaptor() {
		return false;
	}

	@Override
	public String getPortalAdminApiPath() {
		return String.format("https://www.example.com:%d/%s/portaladmin", getPortalPort(), getPortalContextPath());
	}

	@Override
	public String getPortalSharingApiPath() {
		return String.format("https://www.example.com:%d/%s/sharing/rest", getPortalPort(), getPortalContextPath());
	}

	@Override
	public String getArcServerAdminUsername() {
		return "admin";
	}

	@Override
	public String getArcServerAdminPassword() {
		return "password";
	}

	@Override
	public String getArcServerUrl() {
		return "https://www.example.com/" + getArcServerContextPath();
	}

	@Override
	public String getArcServerContextPath() {
		return "arcgis";
	}

	@Override
	public boolean isArcServerUsingWebAdaptor() {
		return false;
	}

	@Override
	public int getArcServerPort() {
		return 6443;
	}

	@Override
	public String getArcServerAdminApiPath() {
		return String.format("https://www.example.com:%d/%s/admin", getArcServerPort(), getArcServerContextPath());
	}
}
