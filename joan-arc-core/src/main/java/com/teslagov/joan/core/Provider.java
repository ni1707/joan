package com.teslagov.joan.core;

/**
 * @author Kevin Chen
 */
public enum Provider {
	ARC_GIS("arcgis"),
	ENTERPRISE("enterprise");

	private final String name;

	Provider(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
