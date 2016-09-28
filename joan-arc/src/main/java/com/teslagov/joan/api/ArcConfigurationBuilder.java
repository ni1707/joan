package com.teslagov.joan.api;

import com.teslagov.joan.core.ArcConfiguration;
import com.teslagov.joan.core.ArcPortalConfiguration;
import com.teslagov.joan.core.ArcServerConfiguration;

/**
 * @author Kevin Chen
 */
public class ArcConfigurationBuilder {

	private ArcServerConfiguration arcServerConfiguration;

	private ArcPortalConfiguration arcPortalConfiguration;

	public static ArcConfigurationBuilder arcConfig() {
		return new ArcConfigurationBuilder();
	}

	public ArcConfigurationBuilder arcServerConfiguration(ArcServerConfiguration arcServerConfiguration) {
		this.arcServerConfiguration = arcServerConfiguration;
		return this;
	}

	public ArcConfigurationBuilder arcPortalConfiguration(ArcPortalConfiguration arcPortalConfiguration) {
		this.arcPortalConfiguration = arcPortalConfiguration;
		return this;
	}

	public ArcConfiguration build() {
		return new ArcConfiguration() {
			@Override
			public ArcServerConfiguration getArcServerConfiguration() {
				return arcServerConfiguration;
			}

			@Override
			public ArcPortalConfiguration getArcPortalConfiguration() {
				return arcPortalConfiguration;
			}
		};
	}
}
