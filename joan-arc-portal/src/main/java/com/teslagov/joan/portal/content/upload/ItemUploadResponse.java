package com.teslagov.joan.portal.content.upload;

import com.teslagov.joan.core.Response;

/**
 * Created by joncrain on 9/20/16.
 */
public class ItemUploadResponse extends Response {
	public Boolean success;
	public String id;
	public String folder;

	@Override
	public String toString() {
		return "ItemUploadResponse{" +
			"success=" + success +
			", id='" + id + '\'' +
			", folder='" + folder + '\'' +
			'}';
	}
}
