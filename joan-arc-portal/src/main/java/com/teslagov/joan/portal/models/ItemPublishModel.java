package com.teslagov.joan.portal.models;

/**
 * Created by joncrain on 9/20/16.
 */
public class ItemPublishModel {
	private String id;
	private String type;
	private String publishParameters;

	public ItemPublishModel(String id, String type, String publishParameters) {
		this.id = id;
		this.type = type;
		this.publishParameters = publishParameters;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getPublishParameters() {
		return publishParameters;
	}

	@Override
	public String toString() {
		return "ItemPublishModel{" +
			"id='" + id + '\'' +
			", type='" + type + '\'' +
			", publishParameters='" + publishParameters + '\'' +
			'}';
	}
}
