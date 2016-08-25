package com.teslagov.joan.portal.group.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class GroupUpdateResponse
{
	public boolean success;

	public String groupId;
}
