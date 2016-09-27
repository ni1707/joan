package com.teslagov.joan.portal.community.group.delete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.core.Response;

/**
 * @author Kevin Chen
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDeleteResponse extends Response {
	public boolean success;

	public String groupId;
}
