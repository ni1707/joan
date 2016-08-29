package com.teslagov.joan.portal.sharing.community.group.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.core.Response;
import com.teslagov.joan.portal.sharing.community.group.Group;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties( ignoreUnknown = true )
public class GroupCreateResponse extends Response
{
	public Group group;

	public boolean success;
}
