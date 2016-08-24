package com.teslagov.joan.portal.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teslagov.joan.Response;

/**
 * @author Kevin Chen
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
public class GroupResponse extends Response
{
	public Group group;

	public boolean success;
}
